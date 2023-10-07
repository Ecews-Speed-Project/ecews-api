package com.ihvncr.ihvncrapi.model.analytics;

import lombok.Data;

import java.util.List;

@Data
public class Series {
    public String name;
    public String id;
    public Long y;
    public String drilldown;
    public Boolean  colorByPoint;
    public Object data;
}
