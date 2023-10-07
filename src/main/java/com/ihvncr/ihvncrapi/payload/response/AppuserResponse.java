package com.ihvncr.ihvncrapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppuserResponse implements Serializable {
    long totalMappedPatients;;
    long totalUnmappedPatients;
    long totalPatients;
    long totalCaseManagers;
    long totalUsers;
    Iterable< ?> users;
}
