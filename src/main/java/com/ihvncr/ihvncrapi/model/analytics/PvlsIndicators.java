package com.ihvncr.ihvncrapi.model.analytics;

import lombok.Data;

import javax.persistence.Tuple;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class PvlsIndicators {
    public Map<String, Object> txPvls ;
    public List<GroupSeries> txPvlsByQuarter ;
    public Map<String, HashMap<String, Object>> txPvlsByAge;
}
