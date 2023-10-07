package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EcewsPatientResponse {
    private String uniqueId;
    private List<EcewsIdentifierInfo> identifiers;
    private EcewsDemographicInfo demographicInfo;
    private EcewsFacilityInfo facilityInfo;
}
