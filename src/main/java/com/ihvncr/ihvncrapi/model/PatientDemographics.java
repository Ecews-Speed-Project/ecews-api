package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDemographics {

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
    private String identifierType;
    private String treatmentGroup;
    private String payForHealthCare;
    private String patientTpe;
    private String dateOfDiagnosis;

    PharmacyEncounter pharmacyEncounter;
    LabEncounter labEncounter;
    Cd4Encounter cd4Encounter;
    ClinicEncounter clinicEncounters;

}

