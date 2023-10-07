package com.ihvncr.ihvncrapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties( { "createDate" ,"updateDate" , })
public class State extends BaseClass implements Serializable {
    private String stateName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
    private Set<LGA> lgas;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
    private Set<Facility> facilities;
}
