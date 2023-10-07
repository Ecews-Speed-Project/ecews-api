package com.ihvncr.ihvncrapi.model;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "flatfile")
public class FlatFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "idgen", sequenceName = "flatfileseq")
    private Integer id;

    private String dataElement;

    private String dataElementName;

    private String period;

    private String categoryOptionCombo;

    private String categoryOptionComboName;

    private String attributeOptionCombo;

    private String value;

    private String implementingPartnerName;

    private String orgUnit;

    private String orgUnitName;

    private String stateName;

    private String lgaName;
}
