package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabEncounter {
    String title;
    String status;
    String cinici;
    int encounterType;
    int encounterId;
    LocalDate returnDate;
    LocalDate encounterDate;
    BigDecimal viralload;
}
