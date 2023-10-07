package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfo {

    private String pepfarId;
    private String patientUniqueId;
    private String facilityCode;
    private String facilityName;
    private String gender;
    private String phoneNumber;
    List<PharmacyEncounter> pharmacyEncounter;
    List<LabEncounter> labEncounter;
    List<ClinicEncounter> clinicEncounters;
}

