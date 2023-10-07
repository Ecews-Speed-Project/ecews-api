package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.payload.request.FacilityDto;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/facility")
@PreAuthorize("hasRole('admin')")
public class FacilityController {
    private final FacilityService facilityService;

    @PostMapping
    public ResponseEntity<MessageResponse> createNewFacility(@RequestBody FacilityDto facilityDto) {
        return new ResponseEntity<>(facilityService.createNewFacility(facilityDto), HttpStatus.CREATED);
    }
}
