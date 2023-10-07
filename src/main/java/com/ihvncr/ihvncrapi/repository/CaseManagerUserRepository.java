package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.CaseManagerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseManagerUserRepository extends JpaRepository<CaseManagerUser, Long> {
}
