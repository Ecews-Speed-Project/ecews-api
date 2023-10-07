package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.FileBatch;
import com.ihvncr.ihvncrapi.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileBatchRepository extends JpaRepository<FileBatch, Long> {
    Optional<FileBatch> findFirstByOrderByUploadDateDesc();
    Page<FileBatch> findAllByUserOrderByUploadDateDesc(User user, Pageable pageable);
    Page<FileBatch> findAllByOrderByUploadDateDesc(Pageable pageable);
    Long countByUserAndStatus(User user, String status);
    Long countByStatus(String status);
    Long countByUser(User user);
}
