package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.Token;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TokenRepository extends PagingAndSortingRepository<Token, Long> {
}
