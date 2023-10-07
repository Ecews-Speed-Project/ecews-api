package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.AppUser;
import com.ihvncr.ihvncrapi.model.Demographics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemographicRepository extends JpaRepository<Demographics, Long> {

    List<Demographics> findDemographicsByPepid(String pepid);

}
