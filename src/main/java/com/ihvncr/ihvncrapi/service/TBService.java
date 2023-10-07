package com.ihvncr.ihvncrapi.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.ihvncr.ihvncrapi.model.ArtLinelist;
import com.ihvncr.ihvncrapi.model.analytics.PvlsIndicators;
import com.ihvncr.ihvncrapi.model.analytics.Search;
import com.ihvncr.ihvncrapi.model.analytics.TXTBIndicators;
import com.ihvncr.ihvncrapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.getCurrentQuarterCode;
import static com.ihvncr.ihvncrapi.utils.CommonUtils.getTupleCriteriaBuilderForStateLgaAndFacility;

@Service
@Slf4j
public class TBService {

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    public final EntityViewManager evm;
    private final HighChatsService highChatsUtil;

    public TBService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm, HighChatsService highChatsUtil) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
        this.highChatsUtil = highChatsUtil;
    }

    public TXTBIndicators  getTBAnalytics(Search search) {
        TXTBIndicators txTbByState = new TXTBIndicators();
        txTbByState.setGetTBCascade(getTBCascade(search));
        txTbByState.setTxTbByState(getTBByState(search));
        return txTbByState;
    }

    public List<Map<String, Object>>   getTBByState(Search search) {
        CriteriaBuilder<Tuple> tbQuery =  getTBQuery(search).select("state ","state");
        List<Map<String, Object>> tbByState = new ArrayList<>();

        List<Tuple>  ageGroupTuples = tbQuery.groupBy("state").getResultList();
        if(!ageGroupTuples.isEmpty()) {
           ageGroupTuples.forEach(txTBData -> {
               HashMap<String, Object> txTBDataResponse = new HashMap<>();
               // calculating % positive yield which is tb positive divided by total screened
               double percentagePositiveYield= (
                       txTBData.get("tx_tb_denominator", Long.class) == CommonUtils.ZERO) ? CommonUtils.ZERO :
                       CommonUtils.calculatePercentage(
                               txTBData.get("tx_tb_positive", Long.class),
                               txTBData.get("tx_tb_denominator", Long.class)
                       );

               // calculating % screening uptake which is tb total screened divided by active patients
               double percentageScreeningUptake= (
                       txTBData.get("tx_tb_denominator", Long.class) == CommonUtils.ZERO) ? CommonUtils.ZERO :
                       CommonUtils.calculatePercentage(
                               txTBData.get("tx_tb_denominator", Long.class),
                               txTBData.get("active", Long.class)
                       );

               txTBDataResponse.put("txCurr", txTBData.get("active"));
               txTBDataResponse.put("txTbDenominator", txTBData.get("tx_tb_denominator"));
               txTBDataResponse.put("positiveScreened", txTBData.get("tx_tb_positive_screen"));
               txTBDataResponse.put("txTbPositive", txTBData.get("tx_tb_positive"));
               txTBDataResponse.put("percentagePositiveYield", percentagePositiveYield);
               txTBDataResponse.put("percentageScreeningUptake", percentageScreeningUptake);
               txTBDataResponse.put("state", txTBData.get("state", String.class));
               tbByState.add(txTBDataResponse);
           });
        }
        return tbByState;
    }

    public Map<String, Object> getTBCascade(Search search) {
        Map<String, Object> txTBDataResponse = new HashMap<>();
        CriteriaBuilder<Tuple> getTBQuery=  getTBQuery(search);
        Tuple txTbData = getTBQuery.getSingleResult();
        if(txTbData.get("tx_tb_denominator") != null) {

            double percentagePresumptive = (
                    txTbData.get("tx_tb_positive", Long.class) == CommonUtils.ZERO) ? CommonUtils.ZERO :
                    CommonUtils.calculatePercentage(
                            txTbData.get("tx_tb_positive", Long.class),
                            txTbData.get("on_treatment", Long.class)
                    );

            // calculating %coverage which is PVLS divided by active patients
            double percentagePositive = (
                    txTbData.get("tx_tb_presumptive", Long.class) == CommonUtils.ZERO) ? CommonUtils.ZERO :
                    CommonUtils.calculatePercentage(
                            txTbData.get("tx_tb_positive", Long.class),
                            txTbData.get("tx_tb_presumptive", Long.class)
                    );

            // calculating %coverage which is PVLS divided by active patients
            double percentageTreatment = (
                    txTbData.get("tx_tb_presumptive", Long.class) == CommonUtils.ZERO) ? CommonUtils.ZERO :
                    CommonUtils.calculatePercentage(
                            txTbData.get("tx_tb_presumptive", Long.class),
                            txTbData.get("tx_tb_denominator", Long.class)
                    );


            txTBDataResponse.put("totalScreened", txTbData.get("tx_tb_denominator"));
            txTBDataResponse.put("screenNegative", txTbData.get("tx_tb_negative"));
            txTBDataResponse.put("tbPresumptive", txTbData.get("tx_tb_presumptive"));
            txTBDataResponse.put("tbNegative", txTbData.get("tx_tb_negative"));
            txTBDataResponse.put("tbPositive", txTbData.get("tx_tb_positive"));
            txTBDataResponse.put("notOnTbTreatment", txTbData.get("not_on_treatment"));
            txTBDataResponse.put("onTbTreatment", txTbData.get("on_treatment"));
            txTBDataResponse.put("percentagePresumptive", percentagePresumptive);
            txTBDataResponse.put("percentagePositive", percentagePositive);
            txTBDataResponse.put("percentageTreatment", percentageTreatment);
            txTBDataResponse.put("positiveScreened", txTbData.get("tx_tb_positive_screen"));
        }else{
            txTBDataResponse.put("totalScreened",CommonUtils.ZERO);
            txTBDataResponse.put("screenNegative", CommonUtils.ZERO);
            txTBDataResponse.put("tbPresumptive", CommonUtils.ZERO);
            txTBDataResponse.put("tbNegative", CommonUtils.ZERO);
            txTBDataResponse.put("tbPositive", CommonUtils.ZERO);
            txTBDataResponse.put("notOnTbTreatment", CommonUtils.ZERO);
            txTBDataResponse.put("onTbTreatment", CommonUtils.ZERO);
            txTBDataResponse.put("percentagePresumptive",CommonUtils.ZERO);
            txTBDataResponse.put("percentagePositive", CommonUtils.ZERO);
            txTBDataResponse.put("percentageTreatment", CommonUtils.ZERO);
            txTBDataResponse.put("positiveScreened", CommonUtils.ZERO);
        }
        return txTBDataResponse;
    }

    public  CriteriaBuilder<Tuple> getTBQuery(Search search) {
        LocalDate cutOff = search.getEndDate();
        CriteriaBuilder<Tuple> cb = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select(
                        "sum(CASE  WHEN currentArtStatus =  'Active'   THEN 1 ELSE 0 END) ",
                        "active")
              .select(
                        "sum(\n" +
                                "CASE  WHEN  tbStatus IS NOT NULL THEN 1 ELSE 0 END)",
                        "tx_tb_denominator")
                .select(
                        "sum(\n" +
                                "CASE  WHEN  tbStatus IS NOT NULL and tbStatus <> 'No signs or symptoms of disease' THEN 1 ELSE 0 END)",
                        "tx_tb_positive_screen")
             .select(
                    "sum(\n" +
                            "CASE  WHEN  tbStatus = 'Disease diagnosed' and tbStatus = 'On treatment for disease' " +
                            "THEN 1 ELSE 0 END ) ",
                    "tx_tb_positive")
            .select(
                    "sum(\n" +
                            "CASE  WHEN  tbStatus IS NOT NULL and tbStatus <> 'No signs or symptoms of disease' " +
                            "THEN 1 ELSE 0 END ) ",
                    "tx_tb_presumptive")
            .select(
                    "sum(\n" +
                            "CASE  WHEN tbStatus = 'Currently on INH prophylaxis'  " +
                            "THEN 1 ELSE 0 END ) ",
                    "tx_tb_negative")
            .select(
                    "sum(\n" +
                            "CASE  WHEN  tbStatus = 'Disease diagnosed' " +
                            "THEN 1 ELSE 0 END ) ",
                    "not_on_treatment")
            .select(
                    "sum(\n" +
                            "CASE  WHEN  tbStatus = 'On treatment for disease' " +
                            "THEN 1 ELSE 0 END ) ",
                    "on_treatment")
                .where("tbStatusDate").between(search.getStartDate()).and(cutOff);
        return getTupleCriteriaBuilderForStateLgaAndFacility(search, cb);
    }

//    static CriteriaBuilder<Tuple> getTupleCriteriaBuilderForStateLgaAndFacility(Search search, CriteriaBuilder<Tuple> cb) {
//        if (!search.getStates().isEmpty()) {
//            cb.where("state").in(search.getStates());
//        }
//        if (!search.getLgas().isEmpty()) {
//            cb.where("lgaName").in(search.getLgas());
//        }
//        if (!search.getFacilities().isEmpty()) {
//            cb.where("facilityName").in(search.getFacilities());
//        }
//        return cb;
//    }


}
