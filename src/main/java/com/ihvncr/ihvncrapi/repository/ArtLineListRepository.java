package com.ihvncr.ihvncrapi.repository;

;
import com.ihvncr.ihvncrapi.model.ArtLinelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface ArtLineListRepository extends JpaRepository<ArtLinelist, Long> {
    List<ArtLinelist> findArtLinelistsByDatimCodeAndQuarter(String datimCode, String quarter);
    @Query(value = "SELECT * from generatedrugpickupperformance()" , nativeQuery = true)
    List<Map<String, Object>> generatedrugpickupperformance();
   List<ArtLinelist> findArtLinelistByPatientUniqueIdAndQuarter(String patientId, String quarter);
   List<ArtLinelist> findArtLinelistByPatientUniqueId(String patientId);
}