package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user", schema = "public", catalog = "ecews")
public class AppUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "is_patient", nullable = false)
    private Boolean isPatient;
    @Basic
    @Column(name = "mobile")
    private String mobile;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "pass_is_default", nullable = false)
    private Boolean passIsDefault;
    @Basic
    @Column(name = "patient_identifier")
    private String patientIdentifier;
    @Basic
    @Column(name = "patient_unique_id")
    private String patientUniqueId;
    @Basic
    @Column(name = "user_type")
    private String userType;
    @Basic
    @Column(name = "master_id")
    private Long masterId;
    @Basic
    @Column(name = "mapper")
    private String mapper;
    @Basic
    @Column(name = "qr_id")
    private String qrId;
    @Basic
    @Column(name = "nfc_id")
    private String nfcId;
    @Basic
    @Column(name = "state")
    private String state;
    @Basic
    @Column(name = "lga")
    private String lga;
    @Basic
    @Column(name = "datim_code")
    private String datimCode;
    @Basic
    @Column(name = "facilityname")
    private String facilityname;
    @Basic
    @Column(name = "KP_Type")
    private String kpType;
    @Basic
    @Column(name = "Name_of_Chort")
    private String nameOfChort;
    @Basic
    @Column(name = "update_date")
    private String updateDate;
    @Basic
    @Column(name = "create_date")
    private String createDate;

}
