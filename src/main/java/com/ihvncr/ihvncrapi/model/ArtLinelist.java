/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihvncr.ihvncrapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author MORRISON.I
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "art_linelist")
@NamedQueries({
        @NamedQuery(name = "ArtLinelist.findAll", query = "SELECT a FROM ArtLinelist a")})
public class ArtLinelist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "state")
    private String state;
    @Column(name = "lga")
    private String lgaName;
    @Column(name = "datim_code")
    private String datimCode;
    @Column(name = "facility_name")
    private String facilityName;
    @Column(name = "patient_unique_id")
    private String patientUniqueId;
    @Column(name = "patient_hospital_no")
    private String patientHospitalNo;
    @Column(name = "anc_no_identifier")
    private String ancNoIdentifier;
    @Column(name = "anc_no_concept_id")
    private String ancNoConceptId;
    @Column(name = "sex")
    private String sex;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "patient_uuid")
    private String patientUuid;
    @Column(name = "age_at_start_of_art_years")
    private Long ageAtStartOfArtYears;
    @Column(name = "age_at_start_of_art_months")
    private Long ageAtStartOfArtMonths;
    @Column(name = "care_entry_point")
    private String careEntryPoint;
    @Column(name = "kp_type")
    private String kpType;
    @Column(name = "months_on_art")
    private Long monthsOnArt;
    @Column(name = "date_transferred_in")
    private LocalDate dateTransferredIn;
    @Column(name = "transfer_in_status")
    private String transferInStatus;
    @Column(name = "art_start_date")
    private LocalDate artStartDate;
    @Column(name = "last_pickup_date")
    private LocalDate lastPickupDate;
    @Column(name = "last_visit_date")
    private LocalDate lastVisitDate;
    @Column(name = "days_of_arv_refil")
    private Long daysOfArvRefil;
    @Column(name = "pill_balance")
    private Long pillBalance;
    @Column(name = "initial_regimen_line")
    private String initialRegimenLine;
    @Column(name = "initial_regimen")
    private String initialRegimen;
    @Column(name = "initial_cd4_count")
    private Long initialCd4Count;
    @Column(name = "initial_cd4_count_date")
    private LocalDate initialCd4CountDate;
    @Column(name = "current_cd4_count")
    private Long currentCd4Count;
    @Column(name = "current_cd4_count_date")
    private LocalDate currentCd4CountDate;
    @Column(name = "last_eac_date")
    private LocalDate lastEacDate;
    @Column(name = "current_regimen_line")
    private String currentRegimenLine;
    @Column(name = "current_regimen")
    private String currentRegimen;
    @Column(name = "pregnancy_status")
    private String pregnancyStatus;
    @Column(name = "pregnancy_status_date")
    private LocalDate pregnancyStatusDate;
    @Column(name = "edd")
    private LocalDate edd;
    @Column(name = "last_delivery_date")
    private LocalDate lastDeliveryDate;
    @Column(name = "lmp")
    private LocalDate lmp;
    @Column(name = "gestation_age_weeks")
    private Long gestationAgeWeeks;
    @Column(name = "current_viral_load")
    private Double currentViralLoad;
    @Column(name = "viral_load_encounter_date")
    private LocalDate viralLoadEncounterDate;
    @Column(name = "viral_load_sample_collection_date")
    private LocalDate viralLoadSampleCollectionDate;
    @Column(name = "viral_load_reported_date")
    private LocalDate viralLoadReportedDate;
    @Column(name = "result_date")
    private LocalDate resultDate;
    @Column(name = "assay_date")
    private LocalDate assayDate;
    @Column(name = "approval_date")
    private LocalDate approvalDate;
    @Column(name = "viral_load_indication")
    private String viralLoadIndication;
    @Column(name = "patient_outcome")
    private String patientOutcome;
    @Column(name = "patient_outcome_date")
    private LocalDate patientOutcomeDate;
    @Column(name = "current_art_status")
    private String currentArtStatus;
    @Column(name = "dispensing_modality")
    private String dispensingModality;
    @Column(name = "facility_dispensing_modality")
    private String facilityDispensingModality;
    @Column(name = "ddd_dispensing_modality")
    private String dddDispensingModality;
    @Column(name = "mmd_type")
    private String mmdType;
    @Column(name = "date_returned_to_care")
    private LocalDate dateReturnedToCare;
    @Column(name = "date_of_termination")
    private LocalDate dateOfTermination;
    @Column(name = "pharmacy_next_appointment")
    private LocalDate pharmacyNextAppointment;
    @Column(name = "clinical_next_appointment")
    private LocalDate clinicalNextAppointment;
    @Column(name = "current_age_yrs")
    private Integer currentAgeYrs;
    @Column(name = "current_age_months")
    private Integer currentAgeMonths;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "mark_as_deceased")
    private String markAsDeceased;
    @Column(name = "mark_as_deceased_death_date")
    private LocalDate markAsDeceasedDeathDate;
    @Column(name = "registration_phone_no")
    private String registrationPhoneNo;
    @Column(name = "next_of_kin_phoneNo")
    private String nextOfKinPhoneNo;
    @Column(name = "treatment_supporter_phoneNo")
    private String treatmentSupporterPhoneNo;
    @Column(name = "biometric_captured")
    private String biometricCaptured;
    @Column(name = "biometric_capture_date")
    private LocalDate biometricCaptureDate;
    @Column(name = "valid_capture")
    private String validCapture;
    @Column(name = "current_weight")
    private Double currentWeight;
    @Column(name = "current_weight_date")
    private LocalDate currentWeightDate;
    @Column(name = "tb_status")
    private String tbStatus;
    @Column(name = "tb_status_date")
    private LocalDate tbStatusDate;
    @Column(name = "baseline_inh_start_date")
    private LocalDate baselineInhStartDate;
    @Column(name = "baseline_inh_stop_date")
    private LocalDate baselineInhStopDate;
    @Column(name = "current_inh_start_date")
    private LocalDate currentInhStartDate;
    @Column(name = "current_inh_outcome")
    private String currentInhOutcome;
    @Column(name = "current_inh_outcome_date")
    private LocalDate currentInhOutcomeDate;
    @Column(name = "last_inh_dispensed_date")
    private LocalDate lastInhDispensedDate;
    @Column(name = "baseline_tb_treatment_start_date")
    private LocalDate baselineTbTreatmentStartDate;
    @Column(name = "baseline_tb_treatment_stop_date")
    private LocalDate baselineTbTreatmentStopDate;
    @Column(name = "last_viral_load_sample_collection_form_date")
    private LocalDate lastViralLoadSampleCollectionFormDate;
    @Column(name = "last_sample_taken_date")
    private LocalDate lastSampleTakenDate;
    @Column(name = "otz_enrollment_date")
    private LocalDate otzEnrollmentDate;
    @Column(name = "otz_outcome_date")
    private LocalDate otzOutcomeDate;
    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;
    @Column(name = "initial_first_line_regimen")
    private String initialFirstLineRegimen;
    @Column(name = "initial_first_line_regimen_date")
    private LocalDate initialFirstLineRegimenDate;
    @Column(name = "initial_second_line_regimen")
    private String initialSecondLineRegimen;
    @Column(name = "initial_second_line_regimen_date")
    private LocalDate initialSecondLineRegimenDate;
    @Column(name = "last_pickup_date_previous_quarter")
    private LocalDate lastPickupDatePreviousQuarter;
    @Column(name = "drug_duration_previous_quarter")
    private Double drugDurationPreviousQuarter;
    @Column(name = "patient_outcome_previous_quarter")
    private String patientOutcomePreviousQuarter;
    @Column(name = "patient_outcome_date_previous_quarter")
    private LocalDate patientOutcomeDatePreviousQuarter;
    @Column(name = "art_status_previous_quarter")
    private String artStatusPreviousQuarter;

    @Column(name = "art_confirmation_date")
    private LocalDate artConfirmationDate;
    @Column(name = "first_pickup_date")
    private LocalDate firstPickupDate;
    @Column(name = "hts_no")
    private String htsNo;
    @Column(name = "last_qty_of_arv_refill")
    private Long lastQtyOfArvRefill;
    private String quarter;
    private String ageRange;
    private boolean hasCriticalError;
}
