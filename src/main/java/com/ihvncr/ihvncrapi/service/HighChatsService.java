package com.ihvncr.ihvncrapi.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.ihvncr.ihvncrapi.model.ArtLinelist;
import com.ihvncr.ihvncrapi.model.analytics.Labels;
import com.ihvncr.ihvncrapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class HighChatsService {
    public  final EntityManager em;
    public   final CriteriaBuilderFactory cbf;
    public  final EntityViewManager evm;

 public HighChatsService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

    public  List<String> getAllQuarter() {
        List<String> quarters = new ArrayList<>();
        CriteriaBuilder<Tuple> cbQuarter= cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("quarter", "quarter")
                .groupBy("quarter")
                .orderByDesc("quarter", true)
                .setMaxResults(5);

        List<Tuple> tuplesQuarter = cbQuarter.getResultList();

        tuplesQuarter.forEach(tuple -> {
            quarters.add(tuple.get("quarter").toString());
        });
        Collections.reverse(quarters);
        return quarters;
    }

    public  List<String> getAllQuarters() {
        List<String> quarters = new ArrayList<>();
        CriteriaBuilder<Tuple> cbQuarter= cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("quarter", "quarter")
                .groupBy("quarter")
                .orderByDesc("quarter", true);
        List<Tuple> tuplesQuarter = cbQuarter.getResultList();
        tuplesQuarter.forEach(tuple -> {
            quarters.add(tuple.get("quarter").toString());
        });
        Collections.reverse(quarters);
        return quarters;
    }

    public  List<String> getLastTwoQuarters() {
        List<String> quarters = new ArrayList<>();
        CriteriaBuilder<Tuple> cbQuarter= cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("quarter", "quarter")
                .groupBy("quarter")
                .orderByDesc("quarter", true)
                .setMaxResults(2);

        List<Tuple> tuplesQuarter = cbQuarter.getResultList();

        tuplesQuarter.forEach(tuple -> {
            quarters.add(tuple.get("quarter").toString());
        });
        return quarters;
    }

    public  List<String> getLatestQuarter() {
        List<String> quarters = new ArrayList<>();
        CriteriaBuilder<Tuple> cbQuarter= cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("quarter", "quarter")
                .groupBy("quarter")
                .orderByDesc("max( lastPickupDate)", true)
                .setMaxResults(1);

        List<Tuple> tuplesQuarter = cbQuarter.getResultList();

        tuplesQuarter.forEach(tuple -> {
            quarters.add(tuple.get("quarter").toString());
        });
        return quarters;
    }

    public List<Labels> getTxCurrByQuarterAndState() {
        List<Labels> txCurrByQuarterAndState = new ArrayList<>();
        CriteriaBuilder<Tuple> cbTxCurr = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("state", "state")
                .select("quarter", "quarter")
                .select("count(*)", "active")
                .where("currentArtStatus").eq("Active")
                .groupBy("quarter")
                .groupBy("state")
                .orderByDesc("quarter", true);
        return getLabels(txCurrByQuarterAndState, cbTxCurr);
    }

    public List<Labels> getIitByQuarterAndState(String quarterCode) {
        List<Labels> iitByQuarterAndState = new ArrayList<>();
        CriteriaBuilder<Tuple> cbIit = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("state", "state")
                .select("quarter", "quarter")
                .select("count(*)", "active")
                .where("quarter").eq(quarterCode)
                .where("currentArtStatus").eq("InActive")
                .where("artStatusPreviousQuarter").eq("Active")
                .where("patientOutcome").isNull()
                .groupBy("quarter").groupBy("state").orderBy("state", true).orderBy("max(lastPickupDate)", true);

        return getLabels(iitByQuarterAndState, cbIit);
    }

    public List<Labels> getTxNewByQuarterAndState(String quarterCode) {
        List<Labels> txNewByQuarterAndState = new ArrayList<>();
        LocalDate startDate = CommonUtils.getQuarterStartDateFromQuarterCode(quarterCode);
        LocalDate endDate = CommonUtils.getQuarterEndDateFromQuarterCode(quarterCode);
        CriteriaBuilder<Tuple> cbTxCurr = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("state", "state")
                .select("quarter", "quarter")
                .select("count(*)", "active")
                .where("quarter").eq(quarterCode)
                .where("currentArtStatus").eq("Active")
                .where("artStartDate").between(startDate).and(endDate)
                .groupBy("quarter").groupBy("state").orderBy("state", true).orderBy("max(lastPickupDate)", true);

        return getLabels(txNewByQuarterAndState, cbTxCurr);
    }

    public List<Labels> getTxPvlsByQuarterAndState(String quarterCode) {
        List<Labels> txPvlsByQuarterAndState = new ArrayList<>();
        LocalDate endDate = CommonUtils.getQuarterEndDateFromQuarterCode(quarterCode);
        LocalDate last12Months = endDate.minusMonths(12);
        LocalDate last3Months = endDate.minusMonths(3);
        double viralLoadCount = 1000;
        CriteriaBuilder<Tuple> cbTxCurr = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("state", "state")
                .select("quarter", "quarter")
                .select("count(*)", "active")
                .where("a.quarter").eq(quarterCode)
                .where("a.currentArtStatus").eq("Active")
                .where("a.currentViralLoad").isNotNull()
                .where("a.currentViralLoad").lt(viralLoadCount)
                .where("a.viralLoadSampleCollectionDate").ge(last12Months)
                .where("a.artStartDate").le(last3Months)
                .groupBy("quarter").groupBy("state").orderBy("state", true).orderBy("max(lastPickupDate)", true);

        return getLabels(txPvlsByQuarterAndState, cbTxCurr);
    }

    private List<Labels> getLabels(List<Labels> labelsByQuarterAndState, CriteriaBuilder<Tuple> cbTuple) {
        List<Tuple> tuples = cbTuple.getResultList();

        tuples.forEach(tuple -> {
            Labels labelsChat = new Labels();
            labelsChat.setState(tuple.get("state").toString());
            labelsChat.setKey(tuple.get("quarter").toString());
            labelsChat.setValue(tuple.get("active", Long.class));
            labelsByQuarterAndState.add(labelsChat);
        });
        return labelsByQuarterAndState;
    }
}
