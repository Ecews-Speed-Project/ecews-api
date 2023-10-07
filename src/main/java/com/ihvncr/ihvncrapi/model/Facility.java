package com.ihvncr.ihvncrapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties( { "createDate" ,"updateDate", "state", "lga" })
@Builder
public class Facility extends BaseClass implements Serializable {
    private String partner;
    private String facilityName;
    @Column(unique = true)
    private String datimCode;
    private String facilityShortName;
    @ManyToOne
    @JoinColumn(name="state_id", referencedColumnName="id")
    @JsonIgnore
    private State state;
    @ManyToOne
    @JoinColumn(name="lga_id", referencedColumnName="id")
    @JsonIgnore
    private LGA lga;
}
