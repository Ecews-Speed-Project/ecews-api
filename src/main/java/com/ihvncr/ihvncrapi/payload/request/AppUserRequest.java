package com.ihvncr.ihvncrapi.payload.request;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRequest {

    private long id;

    @NotNull(message = "mobile cannot be null")
    @NotEmpty(message = "mobile cannot be empty")
    private String mobile;

    @NotNull(message = "first name cannot be null")
    @NotEmpty(message = "first name cannot be empty")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    @NotEmpty(message = "last anme cannot be empty")
    private String lastName;

    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    private String email;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    private String password;

    @NotNull(message = "user type cannot be null")
    @NotEmpty(message = "user cannot be empty")
    private String userType;

    @NotNull(message = "is patient cannot be null")
    @NotEmpty(message = "is patient cannot be empty")
    private boolean isPatient;

    @NotNull(message = "ART number type cannot be null")
    @NotEmpty(message = "ART number cannot be empty")
    private String artNumber;

    @NotNull(message = "QRId cannot be null")
    @NotEmpty(message = "QRId cannot be empty")
    private String qrId;

}
