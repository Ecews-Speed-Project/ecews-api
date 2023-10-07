package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {


    Page<AppUser> findByUserType(String type, Pageable pageable);
    Optional<AppUser> findByMobile(String mobile);

    Optional<AppUser> findByPatientIdentifier(String data);
    List<AppUser> findAppUserByMasterId(Long id);
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUserType(String type);
    Optional<AppUser> findByEmailAndPassword(String email, String password);

}
