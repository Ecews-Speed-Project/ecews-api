package com.ihvncr.ihvncrapi.payload.request;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRescheduleRequest {

    private long id;

    @NotNull(message = "Patient Identifier cannot be null")
    @NotEmpty(message = "Patient Identifier cannot be empty")
    private String patientIdentifier;

    @NotNull(message = "Patient UniqueId cannot be null")
    @NotEmpty(message = "Patient UniqueId cannot be empty")
    private String patientUniqueId;

    @NotNull(message = "return date cannot be null")
    @NotEmpty(message = "return date cannot be empty")
    private LocalDate returnDate;

    @NotNull(message = "new encounter date cannot be null")
    @NotEmpty(message = "new encounter date cannot be empty")
    private LocalDate newEncounterDate;

    @NotNull(message = "encounter type cannot be null")
    @NotEmpty(message = "encounter type date cannot be empty")
    private int encounterType;

    @NotNull(message = "encounter Id cannot be null")
    @NotEmpty(message = "encounter Id  cannot be empty")
    private int encounterId;

}
