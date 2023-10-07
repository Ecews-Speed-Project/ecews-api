package com.ihvncr.ihvncrapi.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "file_upload")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class FileUpload {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long fileUploadId;
    private String facilityDatimcode;
    private String fileName;
    private Timestamp fileTimestamp;
    private Date uploadDate;
    private Date consumerDate;
    private Date validatorDate;
    private Date deduplicationDate;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String schemaValidationReport;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String dataValidationReport;
    private Date etlDate;
    private String status;
    private String patientUuid;
    private boolean hasCriticalError;
    @ManyToOne
    @JoinColumn(name = "file_batch_id", referencedColumnName = "fileBatchId")
    private FileBatch fileBatchId;




}
