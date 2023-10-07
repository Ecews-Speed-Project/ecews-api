package com.ihvncr.ihvncrapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCount {
    private String state;
    private String lga;
    private String facility;
    private String sex;
    private String ageRange ;
    private Long count;
}
