package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
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
    private Date dob;
    private int currentAge;
    private String bp;
    private String occupation;
    private String height;
    private String weight;

    List<PharmacyEncounter> pharmacyEncounter;
    List<LabEncounter> labEncounter;
    List<ClinicEncounter> clinicEncounters;
}

