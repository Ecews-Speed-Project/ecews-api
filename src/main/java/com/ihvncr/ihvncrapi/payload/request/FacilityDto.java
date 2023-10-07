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
public class FacilityDto {
    @NotBlank(message = "Partner cannot be blank")
    private String partner;
    @NotBlank(message = "Facility name cannot be blank")
    private String facilityName;
    @NotBlank(message = "Datim code cannot be blank")
    private String datimCode;
    private String facilityShortName;
    @NotBlank(message = "State cannot be blank")
    private long state;
    @NotBlank(message = "LGA cannot be blank")
    private long lga;
}
