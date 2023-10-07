package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findByStateName(String stateName);
}
