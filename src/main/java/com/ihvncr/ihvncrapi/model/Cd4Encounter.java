package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cd4Encounter {
    String title;
    String status;
    String clinician;
    int encounterType;
    long encounterId;
    Date returnDate;
    Date encounterDate;
    BigDecimal cd4;
}
