package com.ihvncr.ihvncrapi.model.analytics;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TXTBIndicators {
    public Map<String, Object> getTBCascade ;
    public List<Map<String, Object>> txTbByState;
}
