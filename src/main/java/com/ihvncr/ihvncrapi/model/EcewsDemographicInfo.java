package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EcewsDemographicInfo {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String phoneNumber;
    private String treatmentGroup;
}
