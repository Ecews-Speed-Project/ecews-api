package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUserName(String username);
    Optional<User> findByUserEmail(String email);
}
