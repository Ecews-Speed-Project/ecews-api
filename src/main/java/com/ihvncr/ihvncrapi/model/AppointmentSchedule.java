package com.ihvncr.ihvncrapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment_schedule")
public class AppointmentSchedule {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Basic
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Basic
    @Column(name = "patient_identifier")
    private String patientIdentifier;
    @Basic
    @Column(name = "patient_unique_id")
    private String patientUniqueId;

    @Basic
    @Column(name = "return_date")
    private LocalDate returnDate;

    @Basic
    @Column(name = "new_encounter_date")
    private LocalDate newEncounterDate;

    @Basic
    @Column(name = "encounter_type")
    private int encounterType;

    @Basic
    @Column(name = "encounter_id")
    private int encounterId;

    @Basic
    @Column(name = "weeks")
    private int weeks;

    @Basic
    @Column(name = "days")
    private int days;

}
