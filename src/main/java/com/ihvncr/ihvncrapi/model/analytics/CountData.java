package com.ihvncr.ihvncrapi.model.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountData{
    public int allPatient;
    public int stateCount;
    public int lgaCount;
    public int facilityCount;
    public int ipCount;
}
