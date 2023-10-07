package com.ihvncr.ihvncrapi.model.analytics;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class DashboardChats {
    public List<Labels> txCurrByState;
    public List<GroupSeries> txCurrByGender;
    public Set<Map.Entry<String, List<Double>>> txCurrByDemography;
    public List<LineChatSeries> lineChatSeries;
    public List<String> categories;
    public double quarterlyTxCurrDiff;
    public List<StatsLabels> dashboardStats;
}
