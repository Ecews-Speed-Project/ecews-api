package com.ihvncr.ihvncrapi.model.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnChart {
    public List<Series> series;
    public List<Series> drillDown;
}
