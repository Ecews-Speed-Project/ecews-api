package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    Optional<Facility> findByDatimCode(String datimCode);

}
