package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.model.OrgUnit;
import com.ihvncr.ihvncrapi.model.State;
import com.ihvncr.ihvncrapi.service.HighChatsService;
import com.ihvncr.ihvncrapi.service.OrgUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/org-unit")
@RequiredArgsConstructor
public class OrgUnitController {
    private final OrgUnitService orgUnitService;
    private final HighChatsService highChatsUtil;

    @GetMapping("/list")
    public OrgUnit getOrgUnit() {
        OrgUnit orgUnit =  new OrgUnit();
        orgUnit.setQuarters(highChatsUtil.getAllQuarters());
        orgUnit.setStates(orgUnitService.getAllStates());
        return orgUnit;
    }

    @GetMapping("/states")
    public ResponseEntity<List<State>> getAllStates() {
        return new ResponseEntity<>(orgUnitService.getAllStates(), OK);
    }

    @GetMapping("/get-facilities")
    public ResponseEntity<List<State>> getFacilities() {
        return new ResponseEntity<>(orgUnitService.getAllStates(), OK);
    }
}
