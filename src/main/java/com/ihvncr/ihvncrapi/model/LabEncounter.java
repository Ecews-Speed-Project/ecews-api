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
public class LabEncounter {
    String title;
    String status;
    String clinician;
    int encounterType;
    long encounterId;
    Date returnDate;
    Date encounterDate;
    BigDecimal viralload;
}
