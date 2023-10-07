package com.ihvncr.ihvncrapi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDto {
    @NotBlank(message = "PatientId cannot be blank")
    private String patientId;
    @NotBlank(message = "Datim code cannot be blank")
    private String datimCode;
}
