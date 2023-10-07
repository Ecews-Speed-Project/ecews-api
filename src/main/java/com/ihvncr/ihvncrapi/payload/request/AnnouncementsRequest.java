package com.ihvncr.ihvncrapi.payload.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class AnnouncementsRequest {

    private  String id;

    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "titlecannot be empty")
    private  String title;

    @NotNull(message = "message cannot be null")
    @NotEmpty(message = "message cannot be empty")
    private  String message;

    @NotNull(message = "image cannot be null")
    @NotEmpty(message = "image  cannot be empty")
    private  String image;

    @NotNull(message = "announcements type cannot be null")
    @NotEmpty(message = "announcements type  cannot be empty")
    private String announcementsType;

    @NotNull(message = "state cannot be null")
    @NotEmpty(message = "state cannot be empty")
    private String state;

    @NotNull(message = "created date cannot be null")
    @NotEmpty(message = "created date cannot be empty")
    private String createdDate;

    @NotNull(message = "created date cannot be null")
    @NotEmpty(message = "created date cannot be empty")
    private  String expireDate;
}
