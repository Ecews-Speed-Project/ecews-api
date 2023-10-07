package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}
