package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyEncounter {
    String title;
    String status;
    int encounterType;
    long encounterId;
    String clinician;
    Date returnDate;
    String currentRegimen;
    Date encounterDate;
    BigDecimal daysOfRefill;
}
