package com.ihvncr.ihvncrapi.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.ihvncr.ihvncrapi.model.ArtLinelist;
import com.ihvncr.ihvncrapi.model.FileBatch;
import com.ihvncr.ihvncrapi.model.analytics.*;
import com.ihvncr.ihvncrapi.model.enums.Indicators;
import com.ihvncr.ihvncrapi.repository.FileBatchRepository;
import com.ihvncr.ihvncrapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.*;

@Service
@Slf4j
@CacheConfig(cacheNames = {"tuples", "tx_curr_tuples"})
public class DashboardService {

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    public final EntityViewManager evm;
    private final HighChatsService highChatsUtil;
    private final FileBatchRepository fileBatchRepository;

    public DashboardService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm, HighChatsService highChatsUtil, FileBatchRepository fileBatchRepository) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
        this.highChatsUtil = highChatsUtil;
        this.fileBatchRepository = fileBatchRepository;
    }

    public String currentQuarterFromDb(){
        List<String> quarters = highChatsUtil.getAllQuarter();
        return !quarters.isEmpty() ? quarters.get(quarters.size() - 1) : "";
    }

    public DashboardChats getDashboardChats() {
//        List<Labels> txCurrBySex = new ArrayList<>();
        DashboardChats dashboardStats = new DashboardChats();
        List<Labels> txCurrByState = new ArrayList<>();
        List<LineChatSeries> lineChatSeriesList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        String latestQuarter = getCurrentQuarterCode(today);
//        String latestQuarter = currentQuarterFromDb();
        CriteriaBuilder<Tuple> currentTxCurrQuery = getTxCurrCountTuple(latestQuarter);
        try {
            Tuple demographyTuple = currentTxCurrQuery
                    .select(
                            "sum(CASE  WHEN currentAgeYrs < 15 THEN 1 END)",
                            "lessThan15")
                    .select(
                            "sum(CASE  WHEN currentAgeYrs >=15  AND currentAgeYrs <= 19 THEN 1 END)",
                            "s15to19")
                    .select(
                            "sum(CASE  WHEN currentAgeYrs >=20  AND currentAgeYrs <= 49 THEN 1 END)",
                            "s20to49")
                    .select(
                            "sum(CASE  WHEN currentAgeYrs >= 50 THEN 1   END)",
                            "greaterThan50")
                    .select(
                            "sum(CASE  WHEN sex  = 'F' THEN 1   END)",
                            "female")
                    .select(
                            "sum(CASE  WHEN sex  = 'M' THEN 1   END)",
                            "male")
                    .getSingleResult();

            List<Tuple> stateTuples = currentTxCurrQuery
                    .select("state", "state")
                    .groupBy("state")
                    .getResultList();

            stateTuples.forEach(tuple -> {
                Labels state = new Labels();
                state.setState(tuple.get("state").toString());
                state.setKey(tuple.get("state").toString());
                state.setValue(tuple.get("count", Long.class));
                txCurrByState.add(state);
            });

            List<Labels> labels = highChatsUtil.getTxCurrByQuarterAndState();
            Collections.reverse(labels);
            Map<String, List<Labels>> groupedTxCurrByStatesAndQuarter = labels.stream()
                    .filter(labels1 -> !labels1.getKey().equals(latestQuarter))
                    .collect(Collectors.groupingBy(Labels::getState));

            stateTuples.forEach(state -> {
                LineChatSeries lineChatSeries = new LineChatSeries();
                List<Labels> currentLabel = groupedTxCurrByStatesAndQuarter
                        .get(state.get("state").toString());
                long[] data;
                if (currentLabel != null) {
                    if (currentLabel.size() > 4)
                        currentLabel = currentLabel.subList(currentLabel.size() - 4, currentLabel.size());
                    data = currentLabel.stream().mapToLong(Labels::getValue).toArray();
                }
                else
                    data = new long[0];
                lineChatSeries.setName(state.get("state").toString());
                lineChatSeries.setData(data);
                lineChatSeriesList.add(lineChatSeries);
            });

            // building line series data
            dashboardStats.setTxCurrByState(txCurrByState);
            dashboardStats.setLineChatSeries(lineChatSeriesList);
            dashboardStats.setCategories(highChatsUtil.getAllQuarter());

            // building dashboard alert data
            dashboardStats.setDashboardStats(getDashboardAlerts(latestQuarter));
            dashboardStats.setQuarterlyTxCurrDiff(quarterlyTxCurrPercentage());

            // building dashboard demography data
            dashboardStats.setTxCurrByDemography(buildDemographyData(demographyTuple).entrySet());

            // building dashboard gender data
            dashboardStats.setTxCurrByGender(buildGenderData(demographyTuple));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dashboardStats;
    }

    public CriteriaBuilder<Tuple> getTxCurrCountTuple(String quarter) {
        return cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("count(*)", "count")
                .where("currentArtStatus").eq("Active")
                .where("quarter").eq(quarter);
    }

    private List<GroupSeries> buildGenderData(Tuple demographyTuple){

        List<GroupSeries> gender = new ArrayList<>();
        GroupSeries groupSeriesFemale =  new GroupSeries();
        groupSeriesFemale.setName("F");
        groupSeriesFemale.setY(demographyTuple.get("female", Long.class));
        gender.add(groupSeriesFemale);

        GroupSeries groupSeriesMale =  new GroupSeries();
        groupSeriesMale.setName("M");
        groupSeriesMale.setY(demographyTuple.get("male", Long.class));
        gender.add(groupSeriesMale);


        return gender;
    }

    private  Map<String, List<Double>> buildDemographyData(Tuple demographyTuple){
        Map<String, List<Double>>  buildDemographyData =  new LinkedHashMap <>();

        List<Double>  lessThan15 = new ArrayList<>();
        long totalCount = demographyTuple.get("count", Long.class);
        long ageRangeLT15 = demographyTuple.get("lessThan15", Long.class) != null ?
                demographyTuple.get("lessThan15", Long.class): 0;
        lessThan15.add((double)ageRangeLT15);
        lessThan15.add(
                CommonUtils.calculatePercentage(
                        ageRangeLT15,
                totalCount
                )
        );
        buildDemographyData.put("<15", lessThan15);


        List<Double> between15to19 = new ArrayList<>();
        long ageRange15to19 = demographyTuple.get("s15to19", Long.class) != null ?
                demographyTuple.get("s15to19", Long.class) : 0;
        between15to19.add((double)ageRange15to19);
        between15to19.add(
                CommonUtils.calculatePercentage(
                        ageRange15to19,
                        totalCount
                )
        );
        buildDemographyData.put("15-19", between15to19);



        List<Double>  s20to49 = new ArrayList<>();
        long ageRange20to49 = demographyTuple.get("s20to49", Long.class) != null ?
                demographyTuple.get("s20to49", Long.class) : 0;
        s20to49.add((double)ageRange20to49);
        s20to49.add(
                CommonUtils.calculatePercentage(
                        ageRange20to49,
                        totalCount
                )
        );
        buildDemographyData.put("20-49", s20to49);

        List<Double>  greaterThan50 = new ArrayList<>();
        long ageRangeGT50 = demographyTuple.get("greaterThan50", Long.class) != null ?
                demographyTuple.get("greaterThan50", Long.class) : 0;
        greaterThan50.add((double)ageRangeGT50);
        greaterThan50.add(
                CommonUtils.calculatePercentage(
                        ageRangeGT50,
                        totalCount
                )
        );
        buildDemographyData.put(">50", greaterThan50);
        return buildDemographyData;
    }

    private List<StatsLabels> getDashboardAlerts(String quarter) {
//        String quarter = getCurrentQuarterCode(LocalDate.now());
        List<StatsLabels> dashboardStats = new ArrayList<>();

        CriteriaBuilder<Tuple> txCurrQuery = getQuery(quarter);

        Tuple txCurr = txCurrQuery.select("count(*)", "txCurr")
                .where("a.currentArtStatus").eq("Active")
                .getSingleResult();

        Tuple txNew = txCurrQuery.select("count(*)", "txNew")
                .where("a.artStartDate").between(getQuarterStartDateFromQuarterCode(quarter)).and(LocalDate.now())
                .getSingleResult();

        CriteriaBuilder<Tuple> iitQuery = getQuery(quarter);

        Tuple iit = iitQuery.select("count(*)", "iit")
                .where("a.currentArtStatus").eq("InActive")
                .where("a.artStatusPreviousQuarter").eq("Active")
                .where("a.patientOutcome").isNull()
                .getSingleResult();

//        FileBatch latestUpload = fileBatchRepository.findFirstByOrderByUploadDateDesc().orElse(null);
        LocalDate uploadDate = LocalDate.now();
//        if (latestUpload != null)
//            uploadDate = latestUpload.getUploadDate().toLocalDate();
//        else
//            uploadDate = LocalDate.now();
        LocalDate previousTwelveMonths = uploadDate.minusMonths(12);
        CriteriaBuilder<Tuple> txPvlsQuery = getQuery(quarter);
        Tuple txPvlsD = txPvlsQuery.select("count(*)", "txPvlsD")
                .where("a.currentArtStatus").eq("Active")
                .where("a.currentViralLoad").isNotNull()
                .where("a.viralLoadSampleCollectionDate").between(previousTwelveMonths).and(uploadDate)
                .where("a.monthsOnArt").ge(6L)
                .getSingleResult();

        Tuple txPvls = txPvlsQuery
                .select("count(*)", "txPvls")
                .where("a.currentViralLoad").lt(VIRAL_LOAD_COUNT)
                .getSingleResult();

        StatsLabels txCurrLabels = new StatsLabels();
        txCurrLabels.setType(Indicators.TX_CURR.name());
        txCurrLabels.setValue(txCurr.get("txCurr", Long.class));
        txCurrLabels.setDescription("from Last Quarter");
        txCurrLabels.setPercentage(quarterlyTxCurrPercentage());
        dashboardStats.add(txCurrLabels);

        StatsLabels txNewLabels = new StatsLabels();
        txNewLabels.setType(Indicators.TX_NEW.name());
        txNewLabels.setValue(txNew.get("txNew", Long.class));
        txNewLabels.setDescription("as at " + uploadDate);
        txNewLabels.setPercentage(quarterlyTxNewPercentage());
        dashboardStats.add(txNewLabels);

        StatsLabels txPvlsLabels = new StatsLabels();
        txPvlsLabels.setType(Indicators.PVLS.name());
        txPvlsLabels.setValue(txPvls.get("txPvls", Long.class));
        long validResults = txPvlsD.get("txPvlsD", Long.class);
        double suppressed = Math.round((double) txPvlsLabels.getValue() * 100 / validResults);
        txPvlsLabels.setDescription(suppressed + "% Suppressed");
        txPvlsLabels.setPercentage(quarterlyTxPvlsPercentage());
        dashboardStats.add(txPvlsLabels);

        StatsLabels iitLabels = new StatsLabels();
        iitLabels.setType(Indicators.IIT.name());
        iitLabels.setValue(iit.get("iit", Long.class));
        iitLabels.setDescription("as at " + uploadDate);
        iitLabels.setPercentage(quarterlyIitPercentage());
        dashboardStats.add(iitLabels);

        return dashboardStats;
    }

    @Cacheable(value = "tuples")
    public CriteriaBuilder<Tuple> getQuery(String quarter) {
        return cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .where("quarter").eq(quarter)
                .setCacheable(true);
    }

    private double quarterlyTxCurrPercentage() {
        List<Long> allTxCurr = new ArrayList<>();
        Set<String> quartersList = new HashSet<>(highChatsUtil.getLastTwoQuarters());
        quartersList.forEach(quarterCode -> {
            List<Labels> quarterGroup = highChatsUtil.getTxCurrByQuarterAndState()
                    .stream()
                    .collect(Collectors.groupingBy(Labels::getKey))
                    .get(quarterCode);
            long txCurr;
            if (quarterGroup != null) {
                txCurr = quarterGroup.stream().mapToLong(Labels::getValue).sum();
            } else
                txCurr = 0;
            allTxCurr.add(txCurr);
        });
        // Calculating  TXCURR % difference
        return getPercentageIncreaseFromPreviousQuarter(allTxCurr);
    }

    private double getPercentageIncreaseFromPreviousQuarter(List<Long> allTxCurr) {
        long lastQuarterTXCurr = allTxCurr.get(0);
        long currentTXCurr = allTxCurr.get(1);
        double percent = ((double) (currentTXCurr - lastQuarterTXCurr) / lastQuarterTXCurr) * 100;
        percent = Math.round(percent * 100);
        percent = percent / 100;
        return percent;
    }

    private double quarterlyTxNewPercentage() {
        List<Long> allTxNew = new ArrayList<>();
        Set<String> quartersList = new HashSet<>(highChatsUtil.getLastTwoQuarters());
        quartersList.forEach(quarterCode -> {
            List<Labels> quarterGroup = highChatsUtil.getTxNewByQuarterAndState(quarterCode)
                    .stream()
                    .collect(Collectors.groupingBy(Labels::getKey))
                    .get(quarterCode);
            long txNew;
            if (quarterGroup != null) {
                txNew = quarterGroup.stream().mapToLong(Labels::getValue).sum();
            } else
                txNew = 0;
            allTxNew.add(txNew);
        });
        // Calculating  TXCURR % difference
        return getPercentageIncreaseFromPreviousQuarter(allTxNew);
    }

    private double quarterlyIitPercentage() {
        List<Long> allIit = new ArrayList<>();
        Set<String> quartersList = new HashSet<>(highChatsUtil.getLastTwoQuarters());
        quartersList.forEach(quarterCode -> {
            List<Labels> quarterGroup = highChatsUtil.getIitByQuarterAndState(quarterCode)
                    .stream()
                    .collect(Collectors.groupingBy(Labels::getKey))
                    .get(quarterCode);
            long iit;
            if (quarterGroup != null) {
                iit = quarterGroup.stream().mapToLong(Labels::getValue).sum();
            } else
                iit = 0;
            allIit.add(iit);
        });
        // Calculating  TXCURR % difference
        return getPercentageIncreaseFromPreviousQuarter(allIit);
    }

    private double quarterlyTxPvlsPercentage() {
        List<Long> allTxPvls = new ArrayList<>();
        Set<String> quartersList = new HashSet<>(highChatsUtil.getLastTwoQuarters());
        quartersList.forEach(quarterCode -> {
            List<Labels> quarterGroup = highChatsUtil.getTxPvlsByQuarterAndState(quarterCode)
                    .stream()
                    .collect(Collectors.groupingBy(Labels::getKey))
                    .get(quarterCode);
            long txPvls;
            if (quarterGroup != null) {
                txPvls = quarterGroup.stream().mapToLong(Labels::getValue).sum();
            } else
                txPvls = 0;
            allTxPvls.add(txPvls);
        });
        // Calculating  TXCURR % difference
        return getPercentageIncreaseFromPreviousQuarter(allTxPvls);
    }
}
