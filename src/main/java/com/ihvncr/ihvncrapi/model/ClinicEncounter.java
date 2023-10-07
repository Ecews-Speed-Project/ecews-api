package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicEncounter {
    String title;
    String status;
    int encounterType;
    int encounterId;
    LocalDate returnDate;
    LocalDate encounterDate;
}
