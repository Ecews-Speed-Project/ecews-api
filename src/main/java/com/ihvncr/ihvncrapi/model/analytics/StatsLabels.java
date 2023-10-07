package com.ihvncr.ihvncrapi.model.analytics;
import lombok.Data;

@Data
public class StatsLabels {
    private String type;
    private long value;
    private double percentage;
    private String description;
}
