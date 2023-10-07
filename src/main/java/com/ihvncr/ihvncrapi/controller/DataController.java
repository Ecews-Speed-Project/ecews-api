package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.model.analytics.*;
import com.ihvncr.ihvncrapi.service.IndicatorsService;
import com.ihvncr.ihvncrapi.service.PvlsService;
import com.ihvncr.ihvncrapi.service.TBService;
import com.ihvncr.ihvncrapi.service.TxMLService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/data")
@Api(value = "", tags = {"Indicators Service"})
@Tag(name = "Indicators Service", description = "Indicators Computation")
@SecurityRequirement(name = "IHVN_CB")
public class DataController {

    private final IndicatorsService txCurrService;
    private final PvlsService pvlsService;
    private final TBService tbService;

    private final TxMLService txMLService;
    public DataController(IndicatorsService txCurrService, PvlsService pvlsService, TBService tbService, TxMLService txMLService) {
        this.txCurrService = txCurrService;
        this.pvlsService = pvlsService;
        this.tbService = tbService;
        this.txMLService = txMLService;
    }

    @GetMapping("/indicators")
    public ColumnChart getIndicatorAnalysis(@ModelAttribute Search search) {
        log.info("SEARCH PARAMS ------- {}", search);
        return txCurrService.getStateTXCurr(search);
    }
    @GetMapping("/get-pvls-analytics")
    public PvlsIndicators getPvlsAnalytics(@ModelAttribute Search search) {
        log.info("SEARCH PARAMS ------- {}", search);
        return pvlsService.getPvlsAnalytics(search);
    }

    @GetMapping("/get-tb-analytics")
    public TXTBIndicators getTBAnalytics(@ModelAttribute Search search) {
        log.info("SEARCH PARAMS ------- {}", search);
        return tbService.getTBAnalytics(search);
    }

    @GetMapping("/get-txml-analytics")
    public List<WaterFallSeries>  getTXMLAnalytics(@ModelAttribute Search search) {
        log.info("SEARCH PARAMS ------- {}", search);
        return txMLService.getTXMLAnalytics(search);
    }

    @GetMapping("/stats")
    public Stats getStats(@ModelAttribute Search search) {
        return txCurrService.getStats(search);
    }

    @GetMapping("/get-age-rage-chart")
    public Map<String, List<?>> getAgeRangeCharts(@ModelAttribute Search search) {
        return txCurrService.getAgeRangeCharts(search);
    }
}
