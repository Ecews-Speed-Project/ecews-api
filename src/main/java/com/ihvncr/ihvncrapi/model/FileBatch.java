package com.ihvncr.ihvncrapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FileBatch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileBatchId;
    private String zipFileName;
    private String batchNumber;
    private LocalDateTime uploadDate;
    private String status;
    @ManyToOne
    private User user;
    @ManyToOne
    private Facility facility;
//    @OneToMany(mappedBy = "fileBatchId")
//    private List<FileUpload> fileUploads;
}
