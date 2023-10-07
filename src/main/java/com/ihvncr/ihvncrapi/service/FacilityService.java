package com.ihvncr.ihvncrapi.service;

import com.ihvncr.ihvncrapi.exception.FacilityAlreadyExistException;
import com.ihvncr.ihvncrapi.exception.ResourceNotFoundException;
import com.ihvncr.ihvncrapi.model.Facility;
import com.ihvncr.ihvncrapi.model.LGA;
import com.ihvncr.ihvncrapi.model.State;
import com.ihvncr.ihvncrapi.payload.request.FacilityDto;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.repository.FacilityRepository;
import com.ihvncr.ihvncrapi.repository.LGARepository;
import com.ihvncr.ihvncrapi.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityRepository facilityRepository;
    private final StateRepository stateRepository;
    private final LGARepository lgaRepository;

    public MessageResponse createNewFacility(FacilityDto facilityDto) {
        Optional<Facility> optionalFacility = facilityRepository.findByDatimCode(facilityDto.getDatimCode());
        if (optionalFacility.isPresent())
            throw new FacilityAlreadyExistException("Facility already exist");
        State state = stateRepository.findById(facilityDto.getState())
                .orElseThrow(()->new ResourceNotFoundException("State not found"));
        LGA lga = lgaRepository.findById(facilityDto.getLga())
                .orElseThrow(()-> new ResourceNotFoundException("LGA not found"));
        Facility facility = Facility.builder()
                .facilityName(facilityDto.getFacilityName())
                .facilityShortName(facilityDto.getFacilityShortName())
                .datimCode(facilityDto.getDatimCode())
                .partner(facilityDto.getPartner())
                .lga(lga)
                .state(state)
                .build();
        facilityRepository.save(facility);
        return new MessageResponse("Facility saved successfully");
    }
}
