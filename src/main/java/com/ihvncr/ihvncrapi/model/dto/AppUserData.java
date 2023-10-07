package com.ihvncr.ihvncrapi.model.dto;

import com.ihvncr.ihvncrapi.model.LabEncounter;
import com.ihvncr.ihvncrapi.model.PharmacyEncounter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserData {
    private Long id;
    private String email;
    private String mobile;
    private String firstName;
    private String lastName;
    private String patientIdentifier;
    private String userType;
    private String patientUniqueId;
    private boolean isPatient;
    private String currentRegimen;
    private Double currentViralLoad;
    private LocalDate currentViralLoadDate;
    private LocalDate lastPickUpdate;
    private Long daysOfRefill;
    private String status;
    private Long totalPatient;
    private String cohort;
    private LocalDate nextAppointment;
}
