package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.model.analytics.ColumnChart;
import com.ihvncr.ihvncrapi.model.analytics.DashboardChats;
import com.ihvncr.ihvncrapi.model.analytics.Search;
import com.ihvncr.ihvncrapi.model.analytics.Stats;
import com.ihvncr.ihvncrapi.service.DashboardService;
import com.ihvncr.ihvncrapi.service.IndicatorsService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/data")
@Api(value = "", tags = {"Dashboard Service"})
@Tag(name = "Dashboard Service", description = "Dashboard Computation")
@SecurityRequirement(name = "IHVN_CB")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashbaord-stats")
    public ResponseEntity<DashboardChats> getDashboardStats() {
        return new ResponseEntity<>(dashboardService.getDashboardChats(), HttpStatus.OK);
    }

}
