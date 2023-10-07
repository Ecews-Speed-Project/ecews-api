package com.ihvncr.ihvncrapi.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.ihvncr.ihvncrapi.model.ArtLinelist;
import com.ihvncr.ihvncrapi.model.analytics.GroupSeries;
import com.ihvncr.ihvncrapi.model.analytics.PvlsIndicators;
import com.ihvncr.ihvncrapi.model.analytics.Search;
import com.ihvncr.ihvncrapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.*;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.getCurrentQuarterCode;
import static com.ihvncr.ihvncrapi.utils.CommonUtils.getTupleCriteriaBuilderForStateLgaAndFacility;

@Service
@Slf4j
public class PvlsService {

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    public final EntityViewManager evm;
    private final HighChatsService highChatsUtil;

    private  final  DashboardService dashboardService;
    public PvlsService(
            EntityManager em,
            CriteriaBuilderFactory cbf,
            EntityViewManager evm,
            HighChatsService highChatsUtil,
            DashboardService dashboardService) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
        this.highChatsUtil = highChatsUtil;
        this.dashboardService = dashboardService;
    }

    public PvlsIndicators  getPvlsAnalytics(Search search) {
        PvlsIndicators pvlsIndicators = new PvlsIndicators();
        pvlsIndicators.setTxPvls(getPvls(search));
        pvlsIndicators.setTxPvlsByAge(getPvlsbyAgeGroup(search));
        pvlsIndicators.setTxPvlsByQuarter(getVlByQuarter(search));
        return pvlsIndicators;
    }

    public Map<String, HashMap<String, Object>>  getPvlsbyAgeGroup(Search search) {
        CriteriaBuilder<Tuple> getPvlsQuery =  getPvlsQuery(search);
        Map<String, HashMap<String, Object>> ageCategories = new HashMap<>();

        List<Tuple>  ageGroupTuples = getPvlsQuery
                .select("ageRange", "ageRange").groupBy("ageRange").getResultList();
        if(!ageGroupTuples.isEmpty()) {
           ageGroupTuples.forEach(txPvlsData -> {
               HashMap<String, Object> txPvlsDataResponse = new HashMap<>();
               // calculating %suppress which is suppressed divided by PVLS
               double percentageSuppressed = (
                       txPvlsData.get("pvls_numerator", Long.class) == CommonUtils.ZERO) ? CommonUtils.ZERO :
                       CommonUtils.calculatePercentage(
                               txPvlsData.get("pvls_numerator", Long.class),
                               txPvlsData.get("pvls_denominator", Long.class)
                       );
               // calculating %coverage which is PVLS divided by active patients
               double percentageCoverage = (
                       txPvlsData.get("pvls_denominator", Long.class) == CommonUtils.ZERO) ? CommonUtils.ZERO :
                       CommonUtils.calculatePercentage(
                               txPvlsData.get("pvls_denominator", Long.class),
                               txPvlsData.get("active", Long.class)
                       );

               txPvlsDataResponse.put("active", txPvlsData.get("active"));
               txPvlsDataResponse.put("eligible", txPvlsData.get("eligible"));
               txPvlsDataResponse.put("pvlsDenominator", txPvlsData.get("pvls_denominator"));
               txPvlsDataResponse.put("pvlsNumerator", txPvlsData.get("pvls_numerator"));
               txPvlsDataResponse.put("percentageSuppressed", percentageSuppressed);
               txPvlsDataResponse.put("percentageCoverage", percentageCoverage);
               ageCategories.put(txPvlsData.get("ageRange", String.class), txPvlsDataResponse);
           });
        }else{
            CommonUtils.AGE_RANGE_FOR_CHART.forEach(ageRange -> {
                HashMap<String, Object> txPvlsDataResponse = new HashMap<>();
                txPvlsDataResponse.put("active", CommonUtils.ZERO);
                txPvlsDataResponse.put("eligible", CommonUtils.ZERO);
                txPvlsDataResponse.put("pvlsDenominator", CommonUtils.ZERO);
                txPvlsDataResponse.put("pvlsNumerator",CommonUtils.ZERO);
                txPvlsDataResponse.put("percentageSuppressed", CommonUtils.ZERO);
                txPvlsDataResponse.put("percentageCoverage", CommonUtils.ZERO);
                ageCategories.put(ageRange, txPvlsDataResponse);
            });
        }
        return ageCategories;
    }

    public Map<String, Object> getPvls(Search search) {
        Map<String, Object> txPvlsDataResponse = new HashMap<>();

        Tuple txPvlsData =  getPvlsQuery(search).getSingleResult();
                           ;
        if(txPvlsData.get("active") != null) {
            double percentageSuppressed = (
                    txPvlsData.get("pvls_numerator", Long.class) == CommonUtils.ZERO) ? CommonUtils.ZERO :
                    CommonUtils.calculatePercentage(
                            txPvlsData.get("pvls_numerator", Long.class),
                            txPvlsData.get("pvls_denominator", Long.class)
                    );
            // calculating %coverage which is PVLS divided by active patients
            double percentageCoverage = (
                    txPvlsData.get("pvls_denominator", Long.class) == CommonUtils.ZERO) ? CommonUtils.ZERO :
                    CommonUtils.calculatePercentage(
                            txPvlsData.get("pvls_denominator", Long.class),
                            txPvlsData.get("active", Long.class)
                    );

            txPvlsDataResponse.put("active", txPvlsData.get("active"));
            txPvlsDataResponse.put("eligible", txPvlsData.get("eligible"));
            txPvlsDataResponse.put("pvlsDenominator", txPvlsData.get("pvls_denominator"));
            txPvlsDataResponse.put("pvlsNumerator", txPvlsData.get("pvls_numerator"));
            txPvlsDataResponse.put("percentageSuppressed", percentageSuppressed);
            txPvlsDataResponse.put("percentageCoverage", percentageCoverage);
        }else{
            txPvlsDataResponse.put("active",CommonUtils.ZERO);
            txPvlsDataResponse.put("eligible", CommonUtils.ZERO);
            txPvlsDataResponse.put("pvlsDenominator", CommonUtils.ZERO);
            txPvlsDataResponse.put("pvlsNumerator", CommonUtils.ZERO);
            txPvlsDataResponse.put("percentageSuppressed",CommonUtils.ZERO);
            txPvlsDataResponse.put("percentageUnsuppressed", CommonUtils.ZERO);
        }
        return txPvlsDataResponse;
    }

    public  List<GroupSeries> getVlByQuarter(Search search) {
        String quarter;
        if (search.getQuarter() != null && !search.getQuarter().isEmpty()) {
            quarter = search.getQuarter();
        } else {
            quarter = getCurrentQuarterCode(LocalDate.now());
        }
        LocalDate cutOff = CommonUtils.getQuarterEndDateFromQuarterCode(quarter);
//        LocalDate oneYearAge = cutOff.minusMonths(12);

        List<GroupSeries> response = new ArrayList<>();

        CriteriaBuilder<Tuple> cb = cbf.create(em, Tuple.class)
        .from(ArtLinelist.class, "a")
        .select(" sum(CASE  WHEN currentViralLoad <= 50  and quarter = quarter THEN 1 ELSE 0 END)  ", "tnd")
        .select(" sum(CASE  WHEN currentViralLoad >= 51 and  currentViralLoad <= 999 and  a.quarter = quarter THEN 1 ELSE 0 END)", "lvr")
        .select("quarter","quarter").where("lastPickupDate").le(cutOff)
        .groupBy("quarter")
        .orderByDesc("quarter", true)
        .setMaxResults(4);

        CriteriaBuilder<Tuple> cb2 =getTupleCriteriaBuilderForStateLgaAndFacility(search, cb);
        cb2.getResultList().forEach(tuple -> {
            GroupSeries groupSeries = new GroupSeries();
            groupSeries.setName(tuple.get("quarter",String.class));
            groupSeries.setY(tuple.get("tnd",Long.class));
            groupSeries.setZ(tuple.get("lvr",Long.class));
            response.add(groupSeries);
        });
        Collections.reverse(response);
        return response;
    }

    public  CriteriaBuilder<Tuple> getPvlsQuery(Search search) {
        String quarter;
        if (search.getQuarter() != null && !search.getQuarter().isEmpty()) {
            quarter = search.getQuarter();
        } else {
            quarter = getCurrentQuarterCode(LocalDate.now());
        }
        LocalDate cutOff = CommonUtils.getQuarterEndDateFromQuarterCode(quarter);
         //
        LocalDate oneYearAge = cutOff.minusMonths(12);
        CriteriaBuilder<Tuple> cb = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select(
                        "sum(CASE  WHEN currentArtStatus =  'Active'   THEN 1 ELSE 0 END) ",
                        "active")
              .select(
                        "sum(\n" +
                                "CASE  WHEN  monthsOnArt >= 6 and currentArtStatus =  'Active'   THEN 1 ELSE 0 END)",
                        "eligible")
             .select(
                    "sum(\n" +
                            "CASE  WHEN  monthsOnArt  >= 6 and currentArtStatus =  'Active' and " +
                            "currentViralLoad is not null  and \n" +
                            "viralLoadSampleCollectionDate between  '"+oneYearAge+"' and  '"+cutOff+"'  \n" +
                            "THEN 1 ELSE 0 END ) ",
                    "pvls_denominator")
            .select(
                    "sum(\n" +
                            "CASE  WHEN  monthsOnArt  >= 6 and currentArtStatus =  'Active' and " +
                            "currentViralLoad is not null  and \n" +
                            "viralLoadSampleCollectionDate between  '"+oneYearAge+"' and  '"+cutOff+"' AND \n" +
                            "currentViralLoad < 1000\n" +
                            "THEN 1 ELSE 0 END ) ",
                    "pvls_numerator")
                .select(
                        "sum(\n" +
                                "CASE  WHEN  \n" +
                                "monthsOnArt >= 6 and \n" +
                                "currentArtStatus =  'Active' and\n" +
                                "currentViralLoad is not null and \n" +
                                "viralLoadSampleCollectionDate between  '"+oneYearAge+"' and  '"+cutOff+"' AND \n" +
                                "currentViralLoad >= 1000 \n" +
                                "THEN 1 ELSE 0 END )",
                        "pvls_Unsuppress")
                .where("quarter").eq(quarter);

        return getTupleCriteriaBuilderForStateLgaAndFacility(search, cb);
    }


}
