package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.model.EcewsPatientResponse;
import com.ihvncr.ihvncrapi.model.PatientInfo;
import com.ihvncr.ihvncrapi.payload.request.DecryptRequest;
import com.ihvncr.ihvncrapi.payload.request.FacilityDto;
import com.ihvncr.ihvncrapi.payload.request.PatientRequestDto;
import com.ihvncr.ihvncrapi.payload.request.PatientSignupDto;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.service.PatientService;
import com.ihvncr.ihvncrapi.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.decrypt;

@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
@RestController
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/getPatientById")
    public ResponseEntity<?> getPatientById(
            @RequestBody PatientRequestDto patientRequestDto
            ) {
        if (patientService.getPatientByIdentifier(patientRequestDto) != null) {
            return new ResponseEntity<>(
                    patientService.getPatientByIdentifier(patientRequestDto), HttpStatus.CREATED);
        }else{
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid Patient");
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody PatientSignupDto patientSignupDto
    ) {
        EcewsPatientResponse patientInfo = patientService.signUp(patientSignupDto);
        if (patientInfo != null) {
            return new ResponseEntity<>(patientInfo, HttpStatus.CREATED);
        }else{
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid Patient");
        }

    }

    @PostMapping("/decrpt-data")
    public ResponseEntity<?> decryptData(
            @RequestBody DecryptRequest decryptRequest
    ) {
        try {
            String initVectorText = "Pul+O9fBovj6N8QDZhnycw\\=\\=";
            String secretKeyText = "Xw1qbVSbkdzgazihl9KzPA\\=\\=";
            String text = "Qe65x4pfZpPWdZ07o4CmrA==";

            String patientInfo = decrypt(decryptRequest.getText(), initVectorText, secretKeyText);
            if (patientInfo != null) {
                return new ResponseEntity<>(patientInfo, HttpStatus.OK);
            } else {
                return ResponseEntity
                        .status(201)
                        .body("Invalid text");
            }
        }catch (Exception e){
            return ResponseEntity
                    .status(201)
                    .body("Invalid text");
        }

    }

}
