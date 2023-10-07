package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.model.analytics.DashboardChats;
import com.ihvncr.ihvncrapi.service.PerformanceDashboardService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/performance")
@Api(value = "", tags = {"Performance Dashboard Service"})
@Tag(name = "Performance Dashboard Service", description = "Performance Dashboard Service")
@SecurityRequirement(name = "IHVN_CB")
public class PerformanceDashboardController {

    private final PerformanceDashboardService performanceDashboardService;

    public PerformanceDashboardController(PerformanceDashboardService performanceDashboardService) {
        this.performanceDashboardService = performanceDashboardService;
    }

    @GetMapping("/dashboard")
    public List<Map<String, Object>>getDashboardStats() {
        return performanceDashboardService.getDashboardChats();
    }

}
