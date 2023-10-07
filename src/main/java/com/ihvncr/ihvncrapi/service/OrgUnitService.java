package com.ihvncr.ihvncrapi.service;

import com.ihvncr.ihvncrapi.model.State;
import com.ihvncr.ihvncrapi.repository.FacilityRepository;
import com.ihvncr.ihvncrapi.repository.LGARepository;
import com.ihvncr.ihvncrapi.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrgUnitService {
    private final StateRepository stateRepository;
//    private final LGARepository lgaRepository;
    private final FacilityRepository facilityRepository;

    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    public List<State> getAllFacilites() {
        return stateRepository.findAll();
    }
}
