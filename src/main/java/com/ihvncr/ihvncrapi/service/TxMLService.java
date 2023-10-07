package com.ihvncr.ihvncrapi.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.ihvncr.ihvncrapi.model.ArtLinelist;
import com.ihvncr.ihvncrapi.model.analytics.Search;
import com.ihvncr.ihvncrapi.model.analytics.WaterFallSeries;
import com.ihvncr.ihvncrapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.*;

@Service
@Slf4j
public class TxMLService {

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    public final EntityViewManager evm;
    private final HighChatsService highChatsUtil;

    public TxMLService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm, HighChatsService highChatsUtil) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
        this.highChatsUtil = highChatsUtil;
    }

    public  List<WaterFallSeries> getTXMLAnalytics(Search search) {
        Map<String, Object> response = new LinkedHashMap<>();
        CriteriaBuilder<Tuple> getTxMlQuery =  getTxMlQuery(search);
        Tuple txTbData = getTxMlQuery.getSingleResult();
        List<WaterFallSeries> waterFallSeries = new ArrayList<>();
        if(txTbData.get("active_previous_qrt") != null) {
            Long expectedTXCURR = txTbData.get("active_previous_qrt", Long.class) + txTbData.get("tx_new_current_qrt", Long.class) + txTbData.get("tx_rtt", Long.class);
            response.put("Active Q2",txTbData.get("active_previous_qrt"));
            response.put("TX_NEW (Q3 FY22)", txTbData.get("tx_new_current_qrt"));
            response.put("TX_RTT (Q3 FY22)", txTbData.get("tx_rtt"));
            response.put("Expected TX_CURR (Q3 FY22)", expectedTXCURR);
            response.put("Stopped (Q3 FY22)", txTbData.get("stopped_current_qrt"));
            response.put("Transferred Out (Q3 FY22)",txTbData.get("to_current_qrt"));
            response.put("Dead (Q3 FY22)", txTbData.get("dead_current_qrt"));
            response.put("IIT < 3 months (Q3 FY22)", txTbData.get("iit_less_than_3mnths_current_qrt"));
            response.put("IIT 3+ months (Q3 FY22)", txTbData.get("iit_greater_than_3mnths_current_qrt"));
            response.put("Current TX_CURR (Q3 FY22)", txTbData.get("tx_curr_current_qrt"));
        }else{
            response.put("Active Q2",CommonUtils.ZERO);
            response.put("TX_NEW Q3", CommonUtils.ZERO);
            response.put("TX_RTT Q3", CommonUtils.ZERO);
            response.put("Expected TX_CURR (Q3 FY22)", CommonUtils.ZERO);
            response.put("Stopped Q3", CommonUtils.ZERO);
            response.put("Transferred Out Q3", CommonUtils.ZERO);
            response.put("Dead Q3", CommonUtils.ZERO);
            response.put("IIT < 3 months Q3", CommonUtils.ZERO);
            response.put("IIT 3+ months Q3", CommonUtils.ZERO);
            response.put("Current TX_CURR Q3", CommonUtils.ZERO);
        }
        for (Map.Entry<String, Object> entry : response.entrySet()) {
            WaterFallSeries waterFallSeries1 = new WaterFallSeries();
            waterFallSeries1.setName(entry.getKey());
            if(
                    entry.getKey().contains("Stopped") ||
                    entry.getKey().contains("Transferred Out") ||
                    entry.getKey().contains("Dead")  ||
                    entry.getKey().contains("IIT < 3 months")
                    //entry.getKey().contains("IIT 3+ months")
            ) {
                waterFallSeries1.setY(Long.parseLong(String.format("-%s", entry.getValue().toString())));
                waterFallSeries1.setColor("#b71a0f");
            }else if(  entry.getKey().contains("Current TX_CURR") || entry.getKey().contains("Expected TX_CURR")){
                waterFallSeries1.setY(null);
            }else if(  entry.getKey().contains("IIT 3+ months")){
                waterFallSeries1.setY(Long.parseLong("-4126"));
                waterFallSeries1.setColor("#b71a0f");
            }else{
                waterFallSeries1.setY(Long.parseLong(entry.getValue().toString()));
            }

            if(entry.getKey().contains("Expected")){
                waterFallSeries1.setIntermediateSum(true);
            }
            if(entry.getKey().contains("Current TX_CURR")){
                waterFallSeries1.setIsSum(true);
            }
            waterFallSeries.add(waterFallSeries1);
        }
        return waterFallSeries;
    }

    public  CriteriaBuilder<Tuple> getTxMlQuery(Search search) {
        String quarter = search.getQuarter();
        LocalDate cutOff = getQuarterEndDateFromQuarterCode(quarter);
        LocalDate firstDateOfCurrentQuarter = getQuarterStartDateFromQuarterCode(quarter);
        String previousQuarter = getPreviousQuarterByDate(firstDateOfCurrentQuarter);
        System.out.println(previousQuarter);

        CriteriaBuilder<Tuple> cb = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select(
                        "sum(CASE  WHEN currentArtStatus =  'Active'  and quarter = '"+previousQuarter+"' THEN 1 ELSE 0 END) ",
                        "active_previous_qrt")
              .select(
                        " sum(CASE  WHEN   (currentArtStatus =  'Active'  and artStartDate BETWEEN '"+firstDateOfCurrentQuarter+"' and  '"+cutOff+"')  and transferInStatus is null and quarter = '"+quarter+"'  THEN 1 ELSE 0 END)",
                        "tx_new_current_qrt")
             .select(
                    "sum(CASE  WHEN currentArtStatus =  'Active' and artStatusPreviousQuarter =  'Inactive' and quarter = '"+quarter+"' THEN 1 ELSE 0 END)",
                    "tx_rtt")
            .select(
                    "sum(CASE  WHEN patientOutcome =  'Stopped' and quarter = '"+quarter+"'   THEN 1 ELSE 0 END) ",
                    "stopped_current_qrt")
            .select(
                    " sum(CASE  WHEN patientOutcome =  'Transferred out' and quarter = '"+quarter+"'   THEN 1 ELSE 0 END) ",
                    "to_current_qrt")
            .select(
                    "sum(CASE  WHEN patientOutcome =  'Death' and quarter = '"+quarter+"'     THEN 1 ELSE 0 END) ",
                    "dead_current_qrt")
            .select(
                    "sum(CASE  WHEN monthsOnArt < 3  and quarter = '"+quarter+"' THEN 1 ELSE 0 END) ",
                    "on_treatment")
            .select(
                    "sum(CASE  WHEN monthsOnArt < 3  and quarter = '"+quarter+"' and currentArtStatus =  'InActive' and artStatusPreviousQuarter = 'Active' and patientOutcome is null THEN 1 ELSE 0 END) ",
                    "iit_less_than_3mnths_current_qrt")
            .select(
                    "sum(CASE  WHEN monthsOnArt >= 3   and quarter = '"+quarter+"' and currentArtStatus =  'InActive' and artStatusPreviousQuarter = 'Active' and patientOutcome is null THEN 1 ELSE 0 END)",
                    "iit_greater_than_3mnths_current_qrt")
            .select(
                    "sum(CASE  WHEN currentArtStatus =  'Active'  and quarter = '"+quarter+"'  THEN 1 ELSE 0 END) ",
                    "tx_curr_current_qrt");

        return getTupleCriteriaBuilderForStateLgaAndFacility(search, cb);
    }


}
