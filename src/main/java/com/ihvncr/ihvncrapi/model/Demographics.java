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
@Table(name = "demographics")
public class Demographics {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
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
    @Column(name = "pepid")
    private String pepid;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "educationallevel")
    private String educationallevel;
    @Basic
    @Column(name = "maritalstatus")
    private String maritalstatus;
    @Basic
    @Column(name = "jobstatus")
    private String jobstatus;
    @Basic
    @Column(name = "phoneno")
    private String phoneno;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "state_of_residence")
    private String stateOfResidence;
    @Basic
    @Column(name = "lga_of_residence")
    private String lgaOfResidence;


}
