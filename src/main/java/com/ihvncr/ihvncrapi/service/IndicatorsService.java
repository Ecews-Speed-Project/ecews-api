package com.ihvncr.ihvncrapi.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.ihvncr.ihvncrapi.controller.dto.GroupCount;
import com.ihvncr.ihvncrapi.model.ArtLinelist;
import com.ihvncr.ihvncrapi.model.analytics.*;
import com.ihvncr.ihvncrapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.getCurrentQuarterCode;
import static com.ihvncr.ihvncrapi.utils.CommonUtils.getQuarter;

@Service
@Slf4j
public class IndicatorsService {

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    public final EntityViewManager evm;
    private final HighChatsService highChatsUtil;

    public IndicatorsService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm, HighChatsService highChatsUtil) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
        this.highChatsUtil = highChatsUtil;
    }

    public ColumnChart getStateTXCurr(Search search) {

        ColumnChart columnChart;

        List<GroupCount> groupCounts = new ArrayList<>();

        CriteriaBuilder<Tuple> cb = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("count(*)", "count")
                .select("state", "state")
                .select("lgaName", "lga")
                .select("facilityName", "facility")
                .select("sex", "sex")
                .select("ageRange", "age_range")
                .groupBy("state")
                .groupBy("lgaName")
                .groupBy("facilityName")
                .groupBy("sex")
                .groupBy("ageRange");

        if (!search.getStates().isEmpty())
            cb = cb.where("state").in(search.getStates());
        if (!search.getLgas().isEmpty())
            cb = cb.where("lgaName").in(search.getLgas());
        if (!search.getFacilities().isEmpty())
            cb = cb.where("facilityName").in(search.getFacilities());
        if (!search.getSex().isEmpty())
            cb = cb.where("sex").in(search.getSex());
        if (!search.getAgeRange().isEmpty())
            cb = cb.where("ageRange").in(search.getAgeRange());

        List<Tuple> tuples = getIndicator(search, cb);

        tuples.forEach(tuple -> {
            GroupCount groupCount = new GroupCount();
            groupCount.setState(tuple.get("state").toString());
            groupCount.setLga(tuple.get("lga").toString());
            groupCount.setFacility(tuple.get("facility").toString());
            groupCount.setSex(tuple.get("sex").toString());
            groupCount.setCount(tuple.get("count", Long.class));
            groupCount.setAgeRange(tuple.get("age_range").toString());
            groupCounts.add(groupCount);
        });

        columnChart = computeSeries(groupCounts, search);

        return columnChart;
    }

    public ColumnChart buildSeriesAndDrills(List<Series> series, List<Series> drills) {
        ColumnChart columnChart = new ColumnChart();
        columnChart.setSeries(series);
        columnChart.setDrillDown(drills);

        return columnChart;
    }

    public ColumnChart computeSeries(List<GroupCount> groupCounts, Search search) {
        ColumnChart columnChart = new ColumnChart();

        Set<String> states = groupCounts.stream()
                .map(GroupCount::getState)
                .collect(Collectors.toSet());

        Map<String, List<GroupCount>> groupedStates = groupCounts.stream()
                .collect(Collectors
                        .groupingBy(GroupCount::getState));

        List<Series> series = new ArrayList<>();
        List<Series> drillDowns = new ArrayList<>();

        Series stateSeries = new Series();
        stateSeries.setName("States");
        stateSeries.setColorByPoint(Boolean.TRUE);
        List<Series> stateSeriesData = new ArrayList<>();

        states.forEach(state -> {
            List<GroupCount> currentState = groupedStates.get(state);
            Long sum = currentState.stream().mapToLong(s -> Math.toIntExact(s.getCount())).sum();

            Series iPStatesSeries = new Series();
            iPStatesSeries.setName(state);
            iPStatesSeries.setId(state);
            if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL))
                iPStatesSeries.setDrilldown(state.toLowerCase());
            iPStatesSeries.setY(sum);
            iPStatesSeries.setColorByPoint(Boolean.TRUE);
            stateSeriesData.add(iPStatesSeries);

            List<Series> computedDrills = computeStateDrillDown(drillDowns, currentState, state, search);
            Collections.copy(drillDowns, computedDrills);

        });

        stateSeries.setData(stateSeriesData);
        series.add(stateSeries);

        if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL)) {
            columnChart.setSeries(series);
            columnChart.setDrillDown(drillDowns);
        } else {
            columnChart.setSeries(drillDowns);
            // columnChart.setDrillDown(drillDowns);
        }

        return columnChart;
    }

    public List<Series> computeStateDrillDown(
            List<Series> drillDowns, List<GroupCount> currentState,
            String stateName, Search search) {

        Map<String, List<GroupCount>> groupedStateLGAs = currentState
                .stream()
                .collect(Collectors
                        .groupingBy(GroupCount::getLga));

        Set<String> lgas = currentState.stream()
                .map(GroupCount::getLga)
                .collect(Collectors.toSet());

        Series lgaSeries = new Series();
        lgaSeries.setName("LGAs");
        lgaSeries.setId(stateName.toLowerCase());
        lgaSeries.setColorByPoint(Boolean.TRUE);
        List<Series> lgaSeriesData = new ArrayList<>();

        lgas.forEach(lga -> {

            List<GroupCount> currentLGA = groupedStateLGAs.get(lga);
            Long sum = currentLGA.stream().mapToLong(s -> Math.toIntExact(s.getCount())).sum();

            Series stateLGAsSeries = new Series();
            stateLGAsSeries.setId(lga);
            stateLGAsSeries.setName(lga);
            if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL))
                stateLGAsSeries.setDrilldown(lga.toLowerCase());
            stateLGAsSeries.setY(sum);
            stateLGAsSeries.setColorByPoint(Boolean.TRUE);

            lgaSeriesData.add(stateLGAsSeries);

            if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL) ||
                    (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_ADVANCE) &&
                            !search.getLgas().isEmpty())) {
                List<Series> computedLGASeries = computeLGADrillDown(drillDowns, currentLGA, lga, search);
                Collections.copy(drillDowns, computedLGASeries);
            }

        });
        lgaSeries.setData(lgaSeriesData);
        if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL) || (
                search.getSearchType().equals(CommonUtils.SEARCH_TYPE_ADVANCE) &&
                        search.getLgas().isEmpty()
        )) {
            drillDowns.add(lgaSeries);
        }

        return drillDowns;
    }

    public List<Series> computeLGADrillDown(
            List<Series> drillDowns, List<GroupCount> currentLGA,
            String lgaName, Search search) {

        Map<String, List<GroupCount>> groupedLGAsFacilities = currentLGA
                .stream()
                .collect(Collectors
                        .groupingBy(GroupCount::getFacility));

        Set<String> facilities = currentLGA.stream()
                .map(GroupCount::getFacility)
                .collect(Collectors.toSet());

        Series facilitySeries = new Series();
        facilitySeries.setName("FACILITIES");
        facilitySeries.setId(lgaName.toLowerCase());
        facilitySeries.setColorByPoint(Boolean.TRUE);
        List<Series> lgaSeriesData = new ArrayList<>();

        facilities.forEach(facility -> {

            List<GroupCount> currentFacility = groupedLGAsFacilities.get(facility);
            Long sum = currentFacility.stream().mapToLong(s -> Math.toIntExact(s.getCount())).sum();

            Series lgaFacilitySeries = new Series();
            lgaFacilitySeries.setId(facility);
            lgaFacilitySeries.setName(facility);
            if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL))
                lgaFacilitySeries.setDrilldown(facility.toLowerCase());
            lgaFacilitySeries.setY(sum);
            lgaFacilitySeries.setColorByPoint(Boolean.TRUE);

            lgaSeriesData.add(lgaFacilitySeries);

            if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL) ||
                    (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_ADVANCE) &&
                            !search.getFacilities().isEmpty())) {
                List<Series> computedFacilitiesSeries =
                        computeFacilityDrillDown(drillDowns, currentFacility, facility, search);
                Collections.copy(drillDowns, computedFacilitiesSeries);
            }

        });
        facilitySeries.setData(lgaSeriesData);
        if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL) || (
                search.getSearchType().equals(CommonUtils.SEARCH_TYPE_ADVANCE) &&
                        search.getFacilities().isEmpty()
        )) {
            drillDowns.add(facilitySeries);
        }

        return drillDowns;

    }

    public List<Series> computeFacilityDrillDown(
            List<Series> drillDowns, List<GroupCount> currentFacility,
            String facilityName, Search search) {

        Map<String, List<GroupCount>> groupedFacilitiesGenders = currentFacility
                .stream()
                .collect(Collectors
                        .groupingBy(GroupCount::getSex));

        Set<String> genders = currentFacility.stream()
                .map(GroupCount::getSex)
                .collect(Collectors.toSet());

        Series genderSeries = new Series();
        genderSeries.setName("SEX");
        genderSeries.setId(facilityName.toLowerCase());
        genderSeries.setColorByPoint(Boolean.TRUE);
        List<Series> genderSeriesData = new ArrayList<>();

        genders.forEach(gender -> {
            String facilityDrillAndId = facilityName.toLowerCase() + "_" + gender.toLowerCase();

            List<GroupCount> currentGender = groupedFacilitiesGenders.get(gender);
            Long sum = currentGender.stream().mapToLong(s -> Math.toIntExact(s.getCount())).sum();

            Series facilityGenderSeries = new Series();
            facilityGenderSeries.setId(gender);
            facilityGenderSeries.setName(gender);
            if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL))
                facilityGenderSeries.setDrilldown(facilityDrillAndId);
            facilityGenderSeries.setY(sum);
            facilityGenderSeries.setColorByPoint(Boolean.TRUE);

            genderSeriesData.add(facilityGenderSeries);

            if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL) ||
                    (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_ADVANCE) &&
                            !search.getSex().isEmpty())) {
                Series computedDrills = computeGenderDrillDown(drillDowns, currentGender, facilityDrillAndId);
                drillDowns.add(computedDrills);
            }

        });
        genderSeries.setData(genderSeriesData);
        if (search.getSearchType().equals(CommonUtils.SEARCH_TYPE_NORMAL) || (
                search.getSearchType().equals(CommonUtils.SEARCH_TYPE_ADVANCE) &&
                        search.getSex().isEmpty()
        )) {
            drillDowns.add(genderSeries);
        }

        return drillDowns;

    }

    public Series computeGenderDrillDown(List<Series> drillDowns, List<GroupCount> currentGender, String genderName) {

        Series genderAgeRangeSeries = new Series();
        genderAgeRangeSeries.setName("AGE DISAGGREGATION");
        genderAgeRangeSeries.setColorByPoint(Boolean.TRUE);
        genderAgeRangeSeries.setId(genderName);

        List<Series> genderAgeRangeSeriesData = new ArrayList<>();

        CommonUtils.AGE_RANGES.forEach(range -> {

            Series ageRangeSeries = new Series();
            GroupCount gc = currentGender.stream()
                    .filter(l -> l.getAgeRange().equalsIgnoreCase(range))
                    .findAny()
                    .orElse(null);
            Long cnt = (gc != null) ? gc.getCount() : 0;
            ageRangeSeries.setName(range);
            ageRangeSeries.setId(range.toLowerCase());
            ageRangeSeries.setY(cnt);
            // ageRangeSeries.setDrilldown(sex.toLowerCase());
            genderAgeRangeSeriesData.add(ageRangeSeries);

        });

        genderAgeRangeSeries.setData(genderAgeRangeSeriesData);
        // drillDowns.add(genderAgeRangeSeries);

        return genderAgeRangeSeries;

    }

    // Getting stats
    public Stats getStats(Search search) {

        Stats stats = new Stats();

        CriteriaBuilder<Tuple> cb = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("count(*)", "count");
        if (!search.getStates().isEmpty()) {
            cb = cb.where("state").in(search.getStates());
            stats.setNumberOfFacilities(getFacilityCount(search));
            stats.setNumberOfStates((long) search.getStates().size());
        } else {
            CriteriaBuilder<Tuple> cbState = cbf.create(em, Tuple.class)
                    .from(ArtLinelist.class, "a")
                    .select("count(distinct state)", "count");
            List<Tuple> tuples = cbState.getResultList();

            tuples.forEach(tuple -> {
                // stats.setTxCurrStateCount(tuple.get("count", Long.class));
                stats.setNumberOfStates(tuple.get("count", Long.class));
                stats.setNumberOfFacilities(getFacilityCount(search));
            });
        }
        List<Tuple> tuples = getIndicator(search, cb);

        tuples.forEach(tuple -> {
            stats.setTxCurrStateCount(tuple.get("count", Long.class));
        });

        return stats;
    }

    private long getFacilityCount(Search search) {

        CriteriaBuilder<Tuple> cbFacilities = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("count(distinct facilityName)", "count");

        if (!search.getStates().isEmpty()) {
            return cbFacilities
                    .where("state").in(search.getStates()).getSingleResult().get("count", Long.class);
        } else if (!search.getFacilities().isEmpty() && !search.getFacilities().isEmpty()) {
            return cbFacilities.where("facilityName").in(search.getFacilities()).getSingleResult().get("count", Long.class);
        } else {
            return cbFacilities.getSingleResult().get("count", Long.class);
        }

    }

    private List<Tuple> getIndicator(Search search, CriteriaBuilder<Tuple> cb) {
        if(search.getIndicator() != null) {
            switch (search.getIndicator()) {
                case TX_CURR:
                    cb = cb.where("currentArtStatus").eq("Active")
                            .where("quarter").eq(search.getQuarter());
                    break;
                case TX_NEW:
                    cb = cb.where("currentArtStatus").eq("Active")
                            .where("artStartDate").between(search.getStartDate()).and(search.getEndDate());
                    break;
                case PVLS:
                    cb = cb.where("a.resultDate").ge(LocalDate.now().minusMonths(12))
                            .where("currentViralLoad").le(BigDecimal.valueOf(1000))
                            .where("quarter").eq(search.getQuarter());;
                    break;
                default:
                    System.out.println("No indicator selected");
                    break;
            }
        }
        return cb.getResultList();
    }

    private CriteriaBuilder<Tuple> queryBuilder(Search search, CriteriaBuilder<Tuple> cb) {
        if(search.getIndicator() != null) {
            switch (search.getIndicator()) {
                case TX_CURR:
                    cb = cb.where("currentArtStatus").eq("Active");
                    break;
                case TX_NEW:

                    cb = cb.where("artStartDate").ge(search.getStartDate())
                            .where("artStartDate").le(search.getEndDate());
                    break;
                case PVLS:
                    cb = cb.where("a.resultDate").ge(LocalDate.now().minusMonths(12))
                            .where("currentViralLoad").le(BigDecimal.valueOf(1000));
                    break;
                default:
                    System.out.println("No indicator selected");
                    break;
            }
            cb = cb.where("quarter").eq(search.getQuarter());

            if (!search.getStates().isEmpty()) {
                cb.where("state").in(search.getStates());
            } else if (!search.getFacilities().isEmpty() && !search.getFacilities().isEmpty()) {
                cb.where("facilityName").in(search.getFacilities());
            }
        }
        return cb;
    }

    public Map<String, List<?>> getAgeRangeCharts(Search search) {

        Map<String, List<?>> response = new HashMap<>();
        List<SlimSeries> ageRages = new ArrayList<>();
        List<GroupSeries> sexGroups = new ArrayList<>();
        CriteriaBuilder<Tuple> ageGroupQuery = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("count(*)", "count")
                .select("ageRange", "ageRange")
                .select("sex", "sex")
                .groupBy("sex")
                .groupBy("ageRange");

        CriteriaBuilder<Tuple> sexGroupQuery = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("count(*)", "count")
                .select("sex", "sex")
                .groupBy("sex");

        List<Tuple> txCurrByAge = queryBuilder(search, ageGroupQuery).getResultList();
        List<Tuple> txCurrBySex = queryBuilder(search, sexGroupQuery).getResultList();
        Map<Object, List<Tuple>> txCurrByAgeRages = txCurrByAge.stream().collect(Collectors.groupingBy(Tuple -> Tuple.get("sex")));
        for (Object key : txCurrByAgeRages.keySet()) {
            List<Tuple> value = txCurrByAgeRages.get(key);
            List<Long> ageRageLabels = new ArrayList<>();
            SlimSeries ageRange = new SlimSeries();

            //build sex group series
            GroupSeries sexGroup = new GroupSeries();
            Tuple sexGroupValue = txCurrBySex.stream().filter(x -> x.get("sex").equals(key.toString())).findAny().orElse(null);

            for (String age : CommonUtils.AGE_RANGES) {
                Tuple ageRangeValue = value.stream().filter(x -> x.get("ageRange").equals(age)).findAny().orElse(null);
                long countOfAgeRange = (Objects.nonNull(ageRangeValue) ? ageRangeValue.get("count", Long.class) : Long.valueOf(0));
                ageRageLabels.add((key.equals(CommonUtils.FEMALE)) ? -1 * countOfAgeRange : countOfAgeRange);
            }

            ageRange.setData(ageRageLabels);
            ageRange.setName(CommonUtils.getMappedValue(key.toString()));
            ageRages.add(ageRange);

            sexGroup.setY(sexGroupValue.get("count", Long.class));
            sexGroup.setName(CommonUtils.getMappedValue(key.toString()));
            sexGroups.add(sexGroup);
        }
        response.put("tx_male_female_age_groups", ageRages);
        response.put("tx_sex", sexGroups);
        return response;
    }

}
