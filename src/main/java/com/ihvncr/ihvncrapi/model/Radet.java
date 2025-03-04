package com.ihvncr.ihvncrapi.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Radet {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "ip" )
    private String ip;
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
    @Column(name = "patient_id")
    private Integer patientId;
    @Basic
    @Column(name = "pepid")
    private String pepid;
    @Basic
    @Column(name = "datim_pepid")
    private String datimPepid;
    @Basic
    @Column(name = "patienthospitalno")
    private String patienthospitalno;
    @Basic
    @Column(name = "previousid")
    private String previousid;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "kptype")
    private String kptype;
    @Basic
    @Column(name = "ageatstartofart")
    private Long ageatstartofart;
    @Basic
    @Column(name = "ageinmonths")
    private Long ageinmonths;
    @Basic
    @Column(name = "artstartdate")
    private String artstartdate;
    @Basic
    @Column(name = "daysonart")
    private Long daysonart;
    @Basic
    @Column(name = "pharmacy_lastpickupdate")
    private Date pharmacyLastpickupdate;
    @Basic
    @Column(name = "pharmacy_lastpickupdate_previousquarter")
    private Date pharmacyLastpickupdatePreviousquarter;
    @Basic
    @Column(name = "clinic_visit_lastdate")
    private Date clinicVisitLastdate;
    @Basic
    @Column(name = "lastpickupdatecal")
    private Date lastpickupdatecal;
    @Basic
    @Column(name = "daysofarvrefill")
    private Long daysofarvrefill;
    @Basic
    @Column(name = "regimenlineatartstart")
    private String regimenlineatartstart;
    @Basic
    @Column(name = "regimenatartstart")
    private String regimenatartstart;
    @Basic
    @Column(name = "currentregimenline")
    private String currentregimenline;
    @Basic
    @Column(name = "currentartregimen")
    private String currentartregimen;
    @Basic
    @Column(name = "dsd_model")
    private String dsdModel;
    @Basic
    @Column(name = "dsd_model_type")
    private String dsdModelType;
    @Basic
    @Column(name = "currentpregnancystatus")
    private String currentpregnancystatus;
    @Basic
    @Column(name = "currentviralload")
    private Long currentviralload;
    @Basic
    @Column(name = "dateofcurrentviralload")
    private String dateofcurrentviralload;
    @Basic
    @Column(name = "dateresultreceivedfacility")
    private Date dateresultreceivedfacility;
    @Basic
    @Column(name = "alphanumeric_viral_load_result")
    private String alphanumericViralLoadResult;
    @Basic
    @Column(name = "lastdateofsamplecollection")
    private String lastdateofsamplecollection;
    @Basic
    @Column(name = "viralloadindication")
    private String viralloadindication;
    @Basic
    @Column(name = "outcomes")
    private String outcomes;
    @Basic
    @Column(name = "outcomes_date")
    private String outcomesDate;
    @Basic
    @Column(name = "cause_of_death")
    private String causeOfDeath;
    @Basic
    @Column(name = "date_of_iit")
    private String dateOfIit;
    @Basic
    @Column(name = "iit_status")
    private String iitStatus;
    @Basic
    @Column(name = "va_cause_of_death")
    private String vaCauseOfDeath;
    @Basic
    @Column(name = "currentartstatus_pharmacy")
    private String currentartstatusPharmacy;
    @Basic
    @Column(name = "currentartstatus")
    private String currentartstatus;
    @Basic
    @Column(name = "artstatus_previousquarter")
    private String artstatusPreviousquarter;
    @Basic
    @Column(name = "currentartstatus_visit")
    private String currentartstatusVisit;
    @Basic
    @Column(name = "dob")
    private Date dob;
    @Basic
    @Column(name = "current_age")
    private Long currentAge;
    @Basic
    @Column(name = "currentage_months")
    private String currentageMonths;
    @Basic
    @Column(name = "ti")
    private String ti;
    @Basic
    @Column(name = "date_transfered_in")
    private String dateTransferedIn;
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
    @Basic
    @Column(name = "lastweight")
    private Long lastweight;
    @Basic
    @Column(name = "lastweightdate")
    private Date lastweightdate;
    @Basic
    @Column(name = "bp")
    private String bp;
    @Basic
    @Column(name = "whostage")
    private String whostage;
    @Basic
    @Column(name = "firsttld_pickup")
    private String firsttldPickup;
    @Basic
    @Column(name = "dateoffirsttld_pickup")
    private Date dateoffirsttldPickup;
    @Basic
    @Column(name = "firstcd4")
    private Long firstcd4;
    @Basic
    @Column(name = "firstcd4date")
    private Date firstcd4Date;
    @Basic
    @Column(name = "indication_ahd")
    private String indicationAhd;
    @Basic
    @Column(name = "cd4_lfa_result")
    private String cd4LfaResult;
    @Basic
    @Column(name = "Other_Test_(TB-LAM_LF-LAM_etc)")
    private String otherTestTbLamLfLamEtc;
    @Basic
    @Column(name = "serology_for_crag_result")
    private String serologyForCragResult;
    @Basic
    @Column(name = "csf_for_crag_result")
    private String csfForCragResult;
    @Basic
    @Column(name = "estimatednextappointmentpharmacy")
    private String estimatednextappointmentpharmacy;
    @Basic
    @Column(name = "next_ap_by_pharmpill")
    private Date nextApByPharmpill;
    @Basic
    @Column(name = "next_ap_by_carecard")
    private Date nextApByCarecard;
    @Basic
    @Column(name = "days_to_schedule")
    private Long daysToSchedule;
    @Basic
    @Column(name = "appointment_status")
    private String appointmentStatus;
    @Basic
    @Column(name = "first_inh_pickupdate")
    private Date firstInhPickupdate;
    @Basic
    @Column(name = "last_inh_pickupdate")
    private Date lastInhPickupdate;
    @Basic
    @Column(name = "currentinhreceived")
    private String currentinhreceived;
    @Basic
    @Column(name = "current_tb_status")
    private String currentTbStatus;
    @Basic
    @Column(name = "dateofcurrent_tbstatus")
    private Date dateofcurrentTbstatus;
    @Basic
    @Column(name = "tbtreatmentstartdate")
    private Date tbtreatmentstartdate;
    @Basic
    @Column(name = "tbtreatmentstopdate")
    private Date tbtreatmentstopdate;
    @Basic
    @Column(name = "pbs")
    private String pbs;
    @Basic
    @Column(name = "pbsdatecreated")
    private Date pbsdatecreated;
    @Basic
    @Column(name = "ipt_screening_date")
    private Date iptScreeningDate;
    @Basic
    @Column(name = "are_you_coughing_currently")
    private String areYouCoughingCurrently;
    @Basic
    @Column(name = "do_you_have_fever")
    private String doYouHaveFever;
    @Basic
    @Column(name = "are_you_losing_weight")
    private String areYouLosingWeight;
    @Basic
    @Column(name = "are_you_having_night_sweats")
    private String areYouHavingNightSweats;
    @Basic
    @Column(name = "history_of_contacts_with_tb_patients")
    private String historyOfContactsWithTbPatients;
    @Basic
    @Column(name = "sputum_afb")
    private String sputumAfb;
    @Basic
    @Column(name = "sputum_afb_result")
    private String sputumAfbResult;
    @Basic
    @Column(name = "genexpert")
    private String genexpert;
    @Basic
    @Column(name = "genexpert_result")
    private String genexpertResult;
    @Basic
    @Column(name = "chest_xray")
    private String chestXray;
    @Basic
    @Column(name = "chest_xray_result")
    private String chestXrayResult;
    @Basic
    @Column(name = "culture")
    private String culture;
    @Basic
    @Column(name = "culture_result")
    private String cultureResult;
    @Basic
    @Column(name = "is_patient_eligible_for_ipt")
    private String isPatientEligibleForIpt;
    @Basic
    @Column(name = "date_enrolled_into_otz")
    private Date dateEnrolledIntoOtz;
    @Basic
    @Column(name = "transitioned_adult_clinic")
    private String transitionedAdultClinic;
    @Basic
    @Column(name = "otz_outcome")
    private String otzOutcome;
    @Basic
    @Column(name = "otz_outcome_date")
    private Date otzOutcomeDate;
    @Basic
    @Column(name = "positive_living")
    private String positiveLiving;
    @Basic
    @Column(name = "treatment_literacy")
    private String treatmentLiteracy;
    @Basic
    @Column(name = "adolescents_participation")
    private String adolescentsParticipation;
    @Basic
    @Column(name = "leadership_training")
    private String leadershipTraining;
    @Basic
    @Column(name = "peer_to_peer_mentoship")
    private String peerToPeerMentoship;
    @Basic
    @Column(name = "role_of_otz")
    private String roleOfOtz;
    @Basic
    @Column(name = "otz_champion_oreintation")
    private String otzChampionOreintation;
    @Basic
    @Column(name = "next_visit_date")
    private Date nextVisitDate;
    @Basic
    @Column(name = "date_generated")
    private Date dateGenerated;
    @Basic
    @Column(name = "unique_id")
    private String uniqueId;
    @Basic
    @Column(name = "recapture")
    private String recapture;
    @Basic
    @Column(name = "date_of_recapture")
    private Date dateOfRecapture;
    @Basic
    @Column(name = "recapture_count")
    private Long recaptureCount;
    @Basic
    @Column(name = "age_band")
    private String ageBand;
    @Basic
    @Column(name = "iit_date")
    private String iitDate;
    @Basic
    @Column(name = "fy23q4")
    private String fy23Q4;
    @Basic
    @Column(name = "IITStatus2")
    private String iitStatus2;
    @Basic
    @Column(name = "IITDate2")
    private String iitDate2;
    @Basic
    @Column(name = "rtt")
    private String rtt;
    @Basic
    @Column(name = "End Date")
    private String endDate;
    @Basic
    @Column(name = "cal_days_art")
    private Integer calDaysArt;
    @Basic
    @Column(name = "fy24q1")
    private String fy24Q1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getDatimCode() {
        return datimCode;
    }

    public void setDatimCode(String datimCode) {
        this.datimCode = datimCode;
    }

    public String getFacilityname() {
        return facilityname;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPepid() {
        return pepid;
    }

    public void setPepid(String pepid) {
        this.pepid = pepid;
    }

    public String getDatimPepid() {
        return datimPepid;
    }

    public void setDatimPepid(String datimPepid) {
        this.datimPepid = datimPepid;
    }

    public String getPatienthospitalno() {
        return patienthospitalno;
    }

    public void setPatienthospitalno(String patienthospitalno) {
        this.patienthospitalno = patienthospitalno;
    }

    public String getPreviousid() {
        return previousid;
    }

    public void setPreviousid(String previousid) {
        this.previousid = previousid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getKptype() {
        return kptype;
    }

    public void setKptype(String kptype) {
        this.kptype = kptype;
    }

    public Long getAgeatstartofart() {
        return ageatstartofart;
    }

    public void setAgeatstartofart(Long ageatstartofart) {
        this.ageatstartofart = ageatstartofart;
    }

    public Long getAgeinmonths() {
        return ageinmonths;
    }

    public void setAgeinmonths(Long ageinmonths) {
        this.ageinmonths = ageinmonths;
    }

    public String getArtstartdate() {
        return artstartdate;
    }

    public void setArtstartdate(String artstartdate) {
        this.artstartdate = artstartdate;
    }

    public Long getDaysonart() {
        return daysonart;
    }

    public void setDaysonart(Long daysonart) {
        this.daysonart = daysonart;
    }

    public Date getPharmacyLastpickupdate() {
        return pharmacyLastpickupdate;
    }

    public void setPharmacyLastpickupdate(Date pharmacyLastpickupdate) {
        this.pharmacyLastpickupdate = pharmacyLastpickupdate;
    }

    public Date getPharmacyLastpickupdatePreviousquarter() {
        return pharmacyLastpickupdatePreviousquarter;
    }

    public void setPharmacyLastpickupdatePreviousquarter(Date pharmacyLastpickupdatePreviousquarter) {
        this.pharmacyLastpickupdatePreviousquarter = pharmacyLastpickupdatePreviousquarter;
    }

    public Date getClinicVisitLastdate() {
        return clinicVisitLastdate;
    }

    public void setClinicVisitLastdate(Date clinicVisitLastdate) {
        this.clinicVisitLastdate = clinicVisitLastdate;
    }

    public Date getLastpickupdatecal() {
        return lastpickupdatecal;
    }

    public void setLastpickupdatecal(Date lastpickupdatecal) {
        this.lastpickupdatecal = lastpickupdatecal;
    }

    public Long getDaysofarvrefill() {
        return daysofarvrefill;
    }

    public void setDaysofarvrefill(Long daysofarvrefill) {
        this.daysofarvrefill = daysofarvrefill;
    }

    public String getRegimenlineatartstart() {
        return regimenlineatartstart;
    }

    public void setRegimenlineatartstart(String regimenlineatartstart) {
        this.regimenlineatartstart = regimenlineatartstart;
    }

    public String getRegimenatartstart() {
        return regimenatartstart;
    }

    public void setRegimenatartstart(String regimenatartstart) {
        this.regimenatartstart = regimenatartstart;
    }

    public String getCurrentregimenline() {
        return currentregimenline;
    }

    public void setCurrentregimenline(String currentregimenline) {
        this.currentregimenline = currentregimenline;
    }

    public String getCurrentartregimen() {
        return currentartregimen;
    }

    public void setCurrentartregimen(String currentartregimen) {
        this.currentartregimen = currentartregimen;
    }

    public String getDsdModel() {
        return dsdModel;
    }

    public void setDsdModel(String dsdModel) {
        this.dsdModel = dsdModel;
    }

    public String getDsdModelType() {
        return dsdModelType;
    }

    public void setDsdModelType(String dsdModelType) {
        this.dsdModelType = dsdModelType;
    }

    public String getCurrentpregnancystatus() {
        return currentpregnancystatus;
    }

    public void setCurrentpregnancystatus(String currentpregnancystatus) {
        this.currentpregnancystatus = currentpregnancystatus;
    }

    public Long getCurrentviralload() {
        return currentviralload;
    }

    public void setCurrentviralload(Long currentviralload) {
        this.currentviralload = currentviralload;
    }

    public String getDateofcurrentviralload() {
        return dateofcurrentviralload;
    }

    public void setDateofcurrentviralload(String dateofcurrentviralload) {
        this.dateofcurrentviralload = dateofcurrentviralload;
    }

    public Date getDateresultreceivedfacility() {
        return dateresultreceivedfacility;
    }

    public void setDateresultreceivedfacility(Date dateresultreceivedfacility) {
        this.dateresultreceivedfacility = dateresultreceivedfacility;
    }

    public String getAlphanumericViralLoadResult() {
        return alphanumericViralLoadResult;
    }

    public void setAlphanumericViralLoadResult(String alphanumericViralLoadResult) {
        this.alphanumericViralLoadResult = alphanumericViralLoadResult;
    }

    public String getLastdateofsamplecollection() {
        return lastdateofsamplecollection;
    }

    public void setLastdateofsamplecollection(String lastdateofsamplecollection) {
        this.lastdateofsamplecollection = lastdateofsamplecollection;
    }

    public String getViralloadindication() {
        return viralloadindication;
    }

    public void setViralloadindication(String viralloadindication) {
        this.viralloadindication = viralloadindication;
    }

    public String getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(String outcomes) {
        this.outcomes = outcomes;
    }

    public String getOutcomesDate() {
        return outcomesDate;
    }

    public void setOutcomesDate(String outcomesDate) {
        this.outcomesDate = outcomesDate;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public String getDateOfIit() {
        return dateOfIit;
    }

    public void setDateOfIit(String dateOfIit) {
        this.dateOfIit = dateOfIit;
    }

    public String getIitStatus() {
        return iitStatus;
    }

    public void setIitStatus(String iitStatus) {
        this.iitStatus = iitStatus;
    }

    public String getVaCauseOfDeath() {
        return vaCauseOfDeath;
    }

    public void setVaCauseOfDeath(String vaCauseOfDeath) {
        this.vaCauseOfDeath = vaCauseOfDeath;
    }

    public String getCurrentartstatusPharmacy() {
        return currentartstatusPharmacy;
    }

    public void setCurrentartstatusPharmacy(String currentartstatusPharmacy) {
        this.currentartstatusPharmacy = currentartstatusPharmacy;
    }

    public String getCurrentartstatus() {
        return currentartstatus;
    }

    public void setCurrentartstatus(String currentartstatus) {
        this.currentartstatus = currentartstatus;
    }

    public String getArtstatusPreviousquarter() {
        return artstatusPreviousquarter;
    }

    public void setArtstatusPreviousquarter(String artstatusPreviousquarter) {
        this.artstatusPreviousquarter = artstatusPreviousquarter;
    }

    public String getCurrentartstatusVisit() {
        return currentartstatusVisit;
    }

    public void setCurrentartstatusVisit(String currentartstatusVisit) {
        this.currentartstatusVisit = currentartstatusVisit;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Long getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(Long currentAge) {
        this.currentAge = currentAge;
    }

    public String getCurrentageMonths() {
        return currentageMonths;
    }

    public void setCurrentageMonths(String currentageMonths) {
        this.currentageMonths = currentageMonths;
    }

    public String getTi() {
        return ti;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    public String getDateTransferedIn() {
        return dateTransferedIn;
    }

    public void setDateTransferedIn(String dateTransferedIn) {
        this.dateTransferedIn = dateTransferedIn;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEducationallevel() {
        return educationallevel;
    }

    public void setEducationallevel(String educationallevel) {
        this.educationallevel = educationallevel;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateOfResidence() {
        return stateOfResidence;
    }

    public void setStateOfResidence(String stateOfResidence) {
        this.stateOfResidence = stateOfResidence;
    }

    public String getLgaOfResidence() {
        return lgaOfResidence;
    }

    public void setLgaOfResidence(String lgaOfResidence) {
        this.lgaOfResidence = lgaOfResidence;
    }

    public Long getLastweight() {
        return lastweight;
    }

    public void setLastweight(Long lastweight) {
        this.lastweight = lastweight;
    }

    public Date getLastweightdate() {
        return lastweightdate;
    }

    public void setLastweightdate(Date lastweightdate) {
        this.lastweightdate = lastweightdate;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getWhostage() {
        return whostage;
    }

    public void setWhostage(String whostage) {
        this.whostage = whostage;
    }

    public String getFirsttldPickup() {
        return firsttldPickup;
    }

    public void setFirsttldPickup(String firsttldPickup) {
        this.firsttldPickup = firsttldPickup;
    }

    public Date getDateoffirsttldPickup() {
        return dateoffirsttldPickup;
    }

    public void setDateoffirsttldPickup(Date dateoffirsttldPickup) {
        this.dateoffirsttldPickup = dateoffirsttldPickup;
    }

    public Long getFirstcd4() {
        return firstcd4;
    }

    public void setFirstcd4(Long firstcd4) {
        this.firstcd4 = firstcd4;
    }

    public Date getFirstcd4Date() {
        return firstcd4Date;
    }

    public void setFirstcd4Date(Date firstcd4Date) {
        this.firstcd4Date = firstcd4Date;
    }

    public String getIndicationAhd() {
        return indicationAhd;
    }

    public void setIndicationAhd(String indicationAhd) {
        this.indicationAhd = indicationAhd;
    }

    public String getCd4LfaResult() {
        return cd4LfaResult;
    }

    public void setCd4LfaResult(String cd4LfaResult) {
        this.cd4LfaResult = cd4LfaResult;
    }

    public String getOtherTestTbLamLfLamEtc() {
        return otherTestTbLamLfLamEtc;
    }

    public void setOtherTestTbLamLfLamEtc(String otherTestTbLamLfLamEtc) {
        this.otherTestTbLamLfLamEtc = otherTestTbLamLfLamEtc;
    }

    public String getSerologyForCragResult() {
        return serologyForCragResult;
    }

    public void setSerologyForCragResult(String serologyForCragResult) {
        this.serologyForCragResult = serologyForCragResult;
    }

    public String getCsfForCragResult() {
        return csfForCragResult;
    }

    public void setCsfForCragResult(String csfForCragResult) {
        this.csfForCragResult = csfForCragResult;
    }

    public String getEstimatednextappointmentpharmacy() {
        return estimatednextappointmentpharmacy;
    }

    public void setEstimatednextappointmentpharmacy(String estimatednextappointmentpharmacy) {
        this.estimatednextappointmentpharmacy = estimatednextappointmentpharmacy;
    }

    public Date getNextApByPharmpill() {
        return nextApByPharmpill;
    }

    public void setNextApByPharmpill(Date nextApByPharmpill) {
        this.nextApByPharmpill = nextApByPharmpill;
    }

    public Date getNextApByCarecard() {
        return nextApByCarecard;
    }

    public void setNextApByCarecard(Date nextApByCarecard) {
        this.nextApByCarecard = nextApByCarecard;
    }

    public Long getDaysToSchedule() {
        return daysToSchedule;
    }

    public void setDaysToSchedule(Long daysToSchedule) {
        this.daysToSchedule = daysToSchedule;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Date getFirstInhPickupdate() {
        return firstInhPickupdate;
    }

    public void setFirstInhPickupdate(Date firstInhPickupdate) {
        this.firstInhPickupdate = firstInhPickupdate;
    }

    public Date getLastInhPickupdate() {
        return lastInhPickupdate;
    }

    public void setLastInhPickupdate(Date lastInhPickupdate) {
        this.lastInhPickupdate = lastInhPickupdate;
    }

    public String getCurrentinhreceived() {
        return currentinhreceived;
    }

    public void setCurrentinhreceived(String currentinhreceived) {
        this.currentinhreceived = currentinhreceived;
    }

    public String getCurrentTbStatus() {
        return currentTbStatus;
    }

    public void setCurrentTbStatus(String currentTbStatus) {
        this.currentTbStatus = currentTbStatus;
    }

    public Date getDateofcurrentTbstatus() {
        return dateofcurrentTbstatus;
    }

    public void setDateofcurrentTbstatus(Date dateofcurrentTbstatus) {
        this.dateofcurrentTbstatus = dateofcurrentTbstatus;
    }

    public Date getTbtreatmentstartdate() {
        return tbtreatmentstartdate;
    }

    public void setTbtreatmentstartdate(Date tbtreatmentstartdate) {
        this.tbtreatmentstartdate = tbtreatmentstartdate;
    }

    public Date getTbtreatmentstopdate() {
        return tbtreatmentstopdate;
    }

    public void setTbtreatmentstopdate(Date tbtreatmentstopdate) {
        this.tbtreatmentstopdate = tbtreatmentstopdate;
    }

    public String getPbs() {
        return pbs;
    }

    public void setPbs(String pbs) {
        this.pbs = pbs;
    }

    public Date getPbsdatecreated() {
        return pbsdatecreated;
    }

    public void setPbsdatecreated(Date pbsdatecreated) {
        this.pbsdatecreated = pbsdatecreated;
    }

    public Date getIptScreeningDate() {
        return iptScreeningDate;
    }

    public void setIptScreeningDate(Date iptScreeningDate) {
        this.iptScreeningDate = iptScreeningDate;
    }

    public String getAreYouCoughingCurrently() {
        return areYouCoughingCurrently;
    }

    public void setAreYouCoughingCurrently(String areYouCoughingCurrently) {
        this.areYouCoughingCurrently = areYouCoughingCurrently;
    }

    public String getDoYouHaveFever() {
        return doYouHaveFever;
    }

    public void setDoYouHaveFever(String doYouHaveFever) {
        this.doYouHaveFever = doYouHaveFever;
    }

    public String getAreYouLosingWeight() {
        return areYouLosingWeight;
    }

    public void setAreYouLosingWeight(String areYouLosingWeight) {
        this.areYouLosingWeight = areYouLosingWeight;
    }

    public String getAreYouHavingNightSweats() {
        return areYouHavingNightSweats;
    }

    public void setAreYouHavingNightSweats(String areYouHavingNightSweats) {
        this.areYouHavingNightSweats = areYouHavingNightSweats;
    }

    public String getHistoryOfContactsWithTbPatients() {
        return historyOfContactsWithTbPatients;
    }

    public void setHistoryOfContactsWithTbPatients(String historyOfContactsWithTbPatients) {
        this.historyOfContactsWithTbPatients = historyOfContactsWithTbPatients;
    }

    public String getSputumAfb() {
        return sputumAfb;
    }

    public void setSputumAfb(String sputumAfb) {
        this.sputumAfb = sputumAfb;
    }

    public String getSputumAfbResult() {
        return sputumAfbResult;
    }

    public void setSputumAfbResult(String sputumAfbResult) {
        this.sputumAfbResult = sputumAfbResult;
    }

    public String getGenexpert() {
        return genexpert;
    }

    public void setGenexpert(String genexpert) {
        this.genexpert = genexpert;
    }

    public String getGenexpertResult() {
        return genexpertResult;
    }

    public void setGenexpertResult(String genexpertResult) {
        this.genexpertResult = genexpertResult;
    }

    public String getChestXray() {
        return chestXray;
    }

    public void setChestXray(String chestXray) {
        this.chestXray = chestXray;
    }

    public String getChestXrayResult() {
        return chestXrayResult;
    }

    public void setChestXrayResult(String chestXrayResult) {
        this.chestXrayResult = chestXrayResult;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getCultureResult() {
        return cultureResult;
    }

    public void setCultureResult(String cultureResult) {
        this.cultureResult = cultureResult;
    }

    public String getIsPatientEligibleForIpt() {
        return isPatientEligibleForIpt;
    }

    public void setIsPatientEligibleForIpt(String isPatientEligibleForIpt) {
        this.isPatientEligibleForIpt = isPatientEligibleForIpt;
    }

    public Date getDateEnrolledIntoOtz() {
        return dateEnrolledIntoOtz;
    }

    public void setDateEnrolledIntoOtz(Date dateEnrolledIntoOtz) {
        this.dateEnrolledIntoOtz = dateEnrolledIntoOtz;
    }

    public String getTransitionedAdultClinic() {
        return transitionedAdultClinic;
    }

    public void setTransitionedAdultClinic(String transitionedAdultClinic) {
        this.transitionedAdultClinic = transitionedAdultClinic;
    }

    public String getOtzOutcome() {
        return otzOutcome;
    }

    public void setOtzOutcome(String otzOutcome) {
        this.otzOutcome = otzOutcome;
    }

    public Date getOtzOutcomeDate() {
        return otzOutcomeDate;
    }

    public void setOtzOutcomeDate(Date otzOutcomeDate) {
        this.otzOutcomeDate = otzOutcomeDate;
    }

    public String getPositiveLiving() {
        return positiveLiving;
    }

    public void setPositiveLiving(String positiveLiving) {
        this.positiveLiving = positiveLiving;
    }

    public String getTreatmentLiteracy() {
        return treatmentLiteracy;
    }

    public void setTreatmentLiteracy(String treatmentLiteracy) {
        this.treatmentLiteracy = treatmentLiteracy;
    }

    public String getAdolescentsParticipation() {
        return adolescentsParticipation;
    }

    public void setAdolescentsParticipation(String adolescentsParticipation) {
        this.adolescentsParticipation = adolescentsParticipation;
    }

    public String getLeadershipTraining() {
        return leadershipTraining;
    }

    public void setLeadershipTraining(String leadershipTraining) {
        this.leadershipTraining = leadershipTraining;
    }

    public String getPeerToPeerMentoship() {
        return peerToPeerMentoship;
    }

    public void setPeerToPeerMentoship(String peerToPeerMentoship) {
        this.peerToPeerMentoship = peerToPeerMentoship;
    }

    public String getRoleOfOtz() {
        return roleOfOtz;
    }

    public void setRoleOfOtz(String roleOfOtz) {
        this.roleOfOtz = roleOfOtz;
    }

    public String getOtzChampionOreintation() {
        return otzChampionOreintation;
    }

    public void setOtzChampionOreintation(String otzChampionOreintation) {
        this.otzChampionOreintation = otzChampionOreintation;
    }

    public Date getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(Date nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public Date getDateGenerated() {
        return dateGenerated;
    }

    public void setDateGenerated(Date dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getRecapture() {
        return recapture;
    }

    public void setRecapture(String recapture) {
        this.recapture = recapture;
    }

    public Date getDateOfRecapture() {
        return dateOfRecapture;
    }

    public void setDateOfRecapture(Date dateOfRecapture) {
        this.dateOfRecapture = dateOfRecapture;
    }

    public Long getRecaptureCount() {
        return recaptureCount;
    }

    public void setRecaptureCount(Long recaptureCount) {
        this.recaptureCount = recaptureCount;
    }

    public String getAgeBand() {
        return ageBand;
    }

    public void setAgeBand(String ageBand) {
        this.ageBand = ageBand;
    }

    public String getIitDate() {
        return iitDate;
    }

    public void setIitDate(String iitDate) {
        this.iitDate = iitDate;
    }

    public String getFy23Q4() {
        return fy23Q4;
    }

    public void setFy23Q4(String fy23Q4) {
        this.fy23Q4 = fy23Q4;
    }

    public String getIitStatus2() {
        return iitStatus2;
    }

    public void setIitStatus2(String iitStatus2) {
        this.iitStatus2 = iitStatus2;
    }

    public String getIitDate2() {
        return iitDate2;
    }

    public void setIitDate2(String iitDate2) {
        this.iitDate2 = iitDate2;
    }

    public String getRtt() {
        return rtt;
    }

    public void setRtt(String rtt) {
        this.rtt = rtt;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getCalDaysArt() {
        return calDaysArt;
    }

    public void setCalDaysArt(Integer calDaysArt) {
        this.calDaysArt = calDaysArt;
    }

    public String getFy24Q1() {
        return fy24Q1;
    }

    public void setFy24Q1(String fy24Q1) {
        this.fy24Q1 = fy24Q1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Radet radet = (Radet) o;

        if (ip != null ? !ip.equals(radet.ip) : radet.ip != null) return false;
        if (state != null ? !state.equals(radet.state) : radet.state != null) return false;
        if (lga != null ? !lga.equals(radet.lga) : radet.lga != null) return false;
        if (datimCode != null ? !datimCode.equals(radet.datimCode) : radet.datimCode != null) return false;
        if (facilityname != null ? !facilityname.equals(radet.facilityname) : radet.facilityname != null) return false;
        if (patientId != null ? !patientId.equals(radet.patientId) : radet.patientId != null) return false;
        if (pepid != null ? !pepid.equals(radet.pepid) : radet.pepid != null) return false;
        if (datimPepid != null ? !datimPepid.equals(radet.datimPepid) : radet.datimPepid != null) return false;
        if (patienthospitalno != null ? !patienthospitalno.equals(radet.patienthospitalno) : radet.patienthospitalno != null)
            return false;
        if (previousid != null ? !previousid.equals(radet.previousid) : radet.previousid != null) return false;
        if (sex != null ? !sex.equals(radet.sex) : radet.sex != null) return false;
        if (kptype != null ? !kptype.equals(radet.kptype) : radet.kptype != null) return false;
        if (ageatstartofart != null ? !ageatstartofart.equals(radet.ageatstartofart) : radet.ageatstartofart != null)
            return false;
        if (ageinmonths != null ? !ageinmonths.equals(radet.ageinmonths) : radet.ageinmonths != null) return false;
        if (artstartdate != null ? !artstartdate.equals(radet.artstartdate) : radet.artstartdate != null) return false;
        if (daysonart != null ? !daysonart.equals(radet.daysonart) : radet.daysonart != null) return false;
        if (pharmacyLastpickupdate != null ? !pharmacyLastpickupdate.equals(radet.pharmacyLastpickupdate) : radet.pharmacyLastpickupdate != null)
            return false;
        if (pharmacyLastpickupdatePreviousquarter != null ? !pharmacyLastpickupdatePreviousquarter.equals(radet.pharmacyLastpickupdatePreviousquarter) : radet.pharmacyLastpickupdatePreviousquarter != null)
            return false;
        if (clinicVisitLastdate != null ? !clinicVisitLastdate.equals(radet.clinicVisitLastdate) : radet.clinicVisitLastdate != null)
            return false;
        if (lastpickupdatecal != null ? !lastpickupdatecal.equals(radet.lastpickupdatecal) : radet.lastpickupdatecal != null)
            return false;
        if (daysofarvrefill != null ? !daysofarvrefill.equals(radet.daysofarvrefill) : radet.daysofarvrefill != null)
            return false;
        if (regimenlineatartstart != null ? !regimenlineatartstart.equals(radet.regimenlineatartstart) : radet.regimenlineatartstart != null)
            return false;
        if (regimenatartstart != null ? !regimenatartstart.equals(radet.regimenatartstart) : radet.regimenatartstart != null)
            return false;
        if (currentregimenline != null ? !currentregimenline.equals(radet.currentregimenline) : radet.currentregimenline != null)
            return false;
        if (currentartregimen != null ? !currentartregimen.equals(radet.currentartregimen) : radet.currentartregimen != null)
            return false;
        if (dsdModel != null ? !dsdModel.equals(radet.dsdModel) : radet.dsdModel != null) return false;
        if (dsdModelType != null ? !dsdModelType.equals(radet.dsdModelType) : radet.dsdModelType != null) return false;
        if (currentpregnancystatus != null ? !currentpregnancystatus.equals(radet.currentpregnancystatus) : radet.currentpregnancystatus != null)
            return false;
        if (currentviralload != null ? !currentviralload.equals(radet.currentviralload) : radet.currentviralload != null)
            return false;
        if (dateofcurrentviralload != null ? !dateofcurrentviralload.equals(radet.dateofcurrentviralload) : radet.dateofcurrentviralload != null)
            return false;
        if (dateresultreceivedfacility != null ? !dateresultreceivedfacility.equals(radet.dateresultreceivedfacility) : radet.dateresultreceivedfacility != null)
            return false;
        if (alphanumericViralLoadResult != null ? !alphanumericViralLoadResult.equals(radet.alphanumericViralLoadResult) : radet.alphanumericViralLoadResult != null)
            return false;
        if (lastdateofsamplecollection != null ? !lastdateofsamplecollection.equals(radet.lastdateofsamplecollection) : radet.lastdateofsamplecollection != null)
            return false;
        if (viralloadindication != null ? !viralloadindication.equals(radet.viralloadindication) : radet.viralloadindication != null)
            return false;
        if (outcomes != null ? !outcomes.equals(radet.outcomes) : radet.outcomes != null) return false;
        if (outcomesDate != null ? !outcomesDate.equals(radet.outcomesDate) : radet.outcomesDate != null) return false;
        if (causeOfDeath != null ? !causeOfDeath.equals(radet.causeOfDeath) : radet.causeOfDeath != null) return false;
        if (dateOfIit != null ? !dateOfIit.equals(radet.dateOfIit) : radet.dateOfIit != null) return false;
        if (iitStatus != null ? !iitStatus.equals(radet.iitStatus) : radet.iitStatus != null) return false;
        if (vaCauseOfDeath != null ? !vaCauseOfDeath.equals(radet.vaCauseOfDeath) : radet.vaCauseOfDeath != null)
            return false;
        if (currentartstatusPharmacy != null ? !currentartstatusPharmacy.equals(radet.currentartstatusPharmacy) : radet.currentartstatusPharmacy != null)
            return false;
        if (currentartstatus != null ? !currentartstatus.equals(radet.currentartstatus) : radet.currentartstatus != null)
            return false;
        if (artstatusPreviousquarter != null ? !artstatusPreviousquarter.equals(radet.artstatusPreviousquarter) : radet.artstatusPreviousquarter != null)
            return false;
        if (currentartstatusVisit != null ? !currentartstatusVisit.equals(radet.currentartstatusVisit) : radet.currentartstatusVisit != null)
            return false;
        if (dob != null ? !dob.equals(radet.dob) : radet.dob != null) return false;
        if (currentAge != null ? !currentAge.equals(radet.currentAge) : radet.currentAge != null) return false;
        if (currentageMonths != null ? !currentageMonths.equals(radet.currentageMonths) : radet.currentageMonths != null)
            return false;
        if (ti != null ? !ti.equals(radet.ti) : radet.ti != null) return false;
        if (dateTransferedIn != null ? !dateTransferedIn.equals(radet.dateTransferedIn) : radet.dateTransferedIn != null)
            return false;
        if (surname != null ? !surname.equals(radet.surname) : radet.surname != null) return false;
        if (firstname != null ? !firstname.equals(radet.firstname) : radet.firstname != null) return false;
        if (educationallevel != null ? !educationallevel.equals(radet.educationallevel) : radet.educationallevel != null)
            return false;
        if (maritalstatus != null ? !maritalstatus.equals(radet.maritalstatus) : radet.maritalstatus != null)
            return false;
        if (jobstatus != null ? !jobstatus.equals(radet.jobstatus) : radet.jobstatus != null) return false;
        if (phoneno != null ? !phoneno.equals(radet.phoneno) : radet.phoneno != null) return false;
        if (address != null ? !address.equals(radet.address) : radet.address != null) return false;
        if (stateOfResidence != null ? !stateOfResidence.equals(radet.stateOfResidence) : radet.stateOfResidence != null)
            return false;
        if (lgaOfResidence != null ? !lgaOfResidence.equals(radet.lgaOfResidence) : radet.lgaOfResidence != null)
            return false;
        if (lastweight != null ? !lastweight.equals(radet.lastweight) : radet.lastweight != null) return false;
        if (lastweightdate != null ? !lastweightdate.equals(radet.lastweightdate) : radet.lastweightdate != null)
            return false;
        if (bp != null ? !bp.equals(radet.bp) : radet.bp != null) return false;
        if (whostage != null ? !whostage.equals(radet.whostage) : radet.whostage != null) return false;
        if (firsttldPickup != null ? !firsttldPickup.equals(radet.firsttldPickup) : radet.firsttldPickup != null)
            return false;
        if (dateoffirsttldPickup != null ? !dateoffirsttldPickup.equals(radet.dateoffirsttldPickup) : radet.dateoffirsttldPickup != null)
            return false;
        if (firstcd4 != null ? !firstcd4.equals(radet.firstcd4) : radet.firstcd4 != null) return false;
        if (firstcd4Date != null ? !firstcd4Date.equals(radet.firstcd4Date) : radet.firstcd4Date != null) return false;
        if (indicationAhd != null ? !indicationAhd.equals(radet.indicationAhd) : radet.indicationAhd != null)
            return false;
        if (cd4LfaResult != null ? !cd4LfaResult.equals(radet.cd4LfaResult) : radet.cd4LfaResult != null) return false;
        if (otherTestTbLamLfLamEtc != null ? !otherTestTbLamLfLamEtc.equals(radet.otherTestTbLamLfLamEtc) : radet.otherTestTbLamLfLamEtc != null)
            return false;
        if (serologyForCragResult != null ? !serologyForCragResult.equals(radet.serologyForCragResult) : radet.serologyForCragResult != null)
            return false;
        if (csfForCragResult != null ? !csfForCragResult.equals(radet.csfForCragResult) : radet.csfForCragResult != null)
            return false;
        if (estimatednextappointmentpharmacy != null ? !estimatednextappointmentpharmacy.equals(radet.estimatednextappointmentpharmacy) : radet.estimatednextappointmentpharmacy != null)
            return false;
        if (nextApByPharmpill != null ? !nextApByPharmpill.equals(radet.nextApByPharmpill) : radet.nextApByPharmpill != null)
            return false;
        if (nextApByCarecard != null ? !nextApByCarecard.equals(radet.nextApByCarecard) : radet.nextApByCarecard != null)
            return false;
        if (daysToSchedule != null ? !daysToSchedule.equals(radet.daysToSchedule) : radet.daysToSchedule != null)
            return false;
        if (appointmentStatus != null ? !appointmentStatus.equals(radet.appointmentStatus) : radet.appointmentStatus != null)
            return false;
        if (firstInhPickupdate != null ? !firstInhPickupdate.equals(radet.firstInhPickupdate) : radet.firstInhPickupdate != null)
            return false;
        if (lastInhPickupdate != null ? !lastInhPickupdate.equals(radet.lastInhPickupdate) : radet.lastInhPickupdate != null)
            return false;
        if (currentinhreceived != null ? !currentinhreceived.equals(radet.currentinhreceived) : radet.currentinhreceived != null)
            return false;
        if (currentTbStatus != null ? !currentTbStatus.equals(radet.currentTbStatus) : radet.currentTbStatus != null)
            return false;
        if (dateofcurrentTbstatus != null ? !dateofcurrentTbstatus.equals(radet.dateofcurrentTbstatus) : radet.dateofcurrentTbstatus != null)
            return false;
        if (tbtreatmentstartdate != null ? !tbtreatmentstartdate.equals(radet.tbtreatmentstartdate) : radet.tbtreatmentstartdate != null)
            return false;
        if (tbtreatmentstopdate != null ? !tbtreatmentstopdate.equals(radet.tbtreatmentstopdate) : radet.tbtreatmentstopdate != null)
            return false;
        if (pbs != null ? !pbs.equals(radet.pbs) : radet.pbs != null) return false;
        if (pbsdatecreated != null ? !pbsdatecreated.equals(radet.pbsdatecreated) : radet.pbsdatecreated != null)
            return false;
        if (iptScreeningDate != null ? !iptScreeningDate.equals(radet.iptScreeningDate) : radet.iptScreeningDate != null)
            return false;
        if (areYouCoughingCurrently != null ? !areYouCoughingCurrently.equals(radet.areYouCoughingCurrently) : radet.areYouCoughingCurrently != null)
            return false;
        if (doYouHaveFever != null ? !doYouHaveFever.equals(radet.doYouHaveFever) : radet.doYouHaveFever != null)
            return false;
        if (areYouLosingWeight != null ? !areYouLosingWeight.equals(radet.areYouLosingWeight) : radet.areYouLosingWeight != null)
            return false;
        if (areYouHavingNightSweats != null ? !areYouHavingNightSweats.equals(radet.areYouHavingNightSweats) : radet.areYouHavingNightSweats != null)
            return false;
        if (historyOfContactsWithTbPatients != null ? !historyOfContactsWithTbPatients.equals(radet.historyOfContactsWithTbPatients) : radet.historyOfContactsWithTbPatients != null)
            return false;
        if (sputumAfb != null ? !sputumAfb.equals(radet.sputumAfb) : radet.sputumAfb != null) return false;
        if (sputumAfbResult != null ? !sputumAfbResult.equals(radet.sputumAfbResult) : radet.sputumAfbResult != null)
            return false;
        if (genexpert != null ? !genexpert.equals(radet.genexpert) : radet.genexpert != null) return false;
        if (genexpertResult != null ? !genexpertResult.equals(radet.genexpertResult) : radet.genexpertResult != null)
            return false;
        if (chestXray != null ? !chestXray.equals(radet.chestXray) : radet.chestXray != null) return false;
        if (chestXrayResult != null ? !chestXrayResult.equals(radet.chestXrayResult) : radet.chestXrayResult != null)
            return false;
        if (culture != null ? !culture.equals(radet.culture) : radet.culture != null) return false;
        if (cultureResult != null ? !cultureResult.equals(radet.cultureResult) : radet.cultureResult != null)
            return false;
        if (isPatientEligibleForIpt != null ? !isPatientEligibleForIpt.equals(radet.isPatientEligibleForIpt) : radet.isPatientEligibleForIpt != null)
            return false;
        if (dateEnrolledIntoOtz != null ? !dateEnrolledIntoOtz.equals(radet.dateEnrolledIntoOtz) : radet.dateEnrolledIntoOtz != null)
            return false;
        if (transitionedAdultClinic != null ? !transitionedAdultClinic.equals(radet.transitionedAdultClinic) : radet.transitionedAdultClinic != null)
            return false;
        if (otzOutcome != null ? !otzOutcome.equals(radet.otzOutcome) : radet.otzOutcome != null) return false;
        if (otzOutcomeDate != null ? !otzOutcomeDate.equals(radet.otzOutcomeDate) : radet.otzOutcomeDate != null)
            return false;
        if (positiveLiving != null ? !positiveLiving.equals(radet.positiveLiving) : radet.positiveLiving != null)
            return false;
        if (treatmentLiteracy != null ? !treatmentLiteracy.equals(radet.treatmentLiteracy) : radet.treatmentLiteracy != null)
            return false;
        if (adolescentsParticipation != null ? !adolescentsParticipation.equals(radet.adolescentsParticipation) : radet.adolescentsParticipation != null)
            return false;
        if (leadershipTraining != null ? !leadershipTraining.equals(radet.leadershipTraining) : radet.leadershipTraining != null)
            return false;
        if (peerToPeerMentoship != null ? !peerToPeerMentoship.equals(radet.peerToPeerMentoship) : radet.peerToPeerMentoship != null)
            return false;
        if (roleOfOtz != null ? !roleOfOtz.equals(radet.roleOfOtz) : radet.roleOfOtz != null) return false;
        if (otzChampionOreintation != null ? !otzChampionOreintation.equals(radet.otzChampionOreintation) : radet.otzChampionOreintation != null)
            return false;
        if (nextVisitDate != null ? !nextVisitDate.equals(radet.nextVisitDate) : radet.nextVisitDate != null)
            return false;
        if (dateGenerated != null ? !dateGenerated.equals(radet.dateGenerated) : radet.dateGenerated != null)
            return false;
        if (uniqueId != null ? !uniqueId.equals(radet.uniqueId) : radet.uniqueId != null) return false;
        if (recapture != null ? !recapture.equals(radet.recapture) : radet.recapture != null) return false;
        if (dateOfRecapture != null ? !dateOfRecapture.equals(radet.dateOfRecapture) : radet.dateOfRecapture != null)
            return false;
        if (recaptureCount != null ? !recaptureCount.equals(radet.recaptureCount) : radet.recaptureCount != null)
            return false;
        if (ageBand != null ? !ageBand.equals(radet.ageBand) : radet.ageBand != null) return false;
        if (iitDate != null ? !iitDate.equals(radet.iitDate) : radet.iitDate != null) return false;
        if (fy23Q4 != null ? !fy23Q4.equals(radet.fy23Q4) : radet.fy23Q4 != null) return false;
        if (iitStatus2 != null ? !iitStatus2.equals(radet.iitStatus2) : radet.iitStatus2 != null) return false;
        if (iitDate2 != null ? !iitDate2.equals(radet.iitDate2) : radet.iitDate2 != null) return false;
        if (rtt != null ? !rtt.equals(radet.rtt) : radet.rtt != null) return false;
        if (endDate != null ? !endDate.equals(radet.endDate) : radet.endDate != null) return false;
        if (calDaysArt != null ? !calDaysArt.equals(radet.calDaysArt) : radet.calDaysArt != null) return false;
        if (fy24Q1 != null ? !fy24Q1.equals(radet.fy24Q1) : radet.fy24Q1 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (lga != null ? lga.hashCode() : 0);
        result = 31 * result + (datimCode != null ? datimCode.hashCode() : 0);
        result = 31 * result + (facilityname != null ? facilityname.hashCode() : 0);
        result = 31 * result + (patientId != null ? patientId.hashCode() : 0);
        result = 31 * result + (pepid != null ? pepid.hashCode() : 0);
        result = 31 * result + (datimPepid != null ? datimPepid.hashCode() : 0);
        result = 31 * result + (patienthospitalno != null ? patienthospitalno.hashCode() : 0);
        result = 31 * result + (previousid != null ? previousid.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (kptype != null ? kptype.hashCode() : 0);
        result = 31 * result + (ageatstartofart != null ? ageatstartofart.hashCode() : 0);
        result = 31 * result + (ageinmonths != null ? ageinmonths.hashCode() : 0);
        result = 31 * result + (artstartdate != null ? artstartdate.hashCode() : 0);
        result = 31 * result + (daysonart != null ? daysonart.hashCode() : 0);
        result = 31 * result + (pharmacyLastpickupdate != null ? pharmacyLastpickupdate.hashCode() : 0);
        result = 31 * result + (pharmacyLastpickupdatePreviousquarter != null ? pharmacyLastpickupdatePreviousquarter.hashCode() : 0);
        result = 31 * result + (clinicVisitLastdate != null ? clinicVisitLastdate.hashCode() : 0);
        result = 31 * result + (lastpickupdatecal != null ? lastpickupdatecal.hashCode() : 0);
        result = 31 * result + (daysofarvrefill != null ? daysofarvrefill.hashCode() : 0);
        result = 31 * result + (regimenlineatartstart != null ? regimenlineatartstart.hashCode() : 0);
        result = 31 * result + (regimenatartstart != null ? regimenatartstart.hashCode() : 0);
        result = 31 * result + (currentregimenline != null ? currentregimenline.hashCode() : 0);
        result = 31 * result + (currentartregimen != null ? currentartregimen.hashCode() : 0);
        result = 31 * result + (dsdModel != null ? dsdModel.hashCode() : 0);
        result = 31 * result + (dsdModelType != null ? dsdModelType.hashCode() : 0);
        result = 31 * result + (currentpregnancystatus != null ? currentpregnancystatus.hashCode() : 0);
        result = 31 * result + (currentviralload != null ? currentviralload.hashCode() : 0);
        result = 31 * result + (dateofcurrentviralload != null ? dateofcurrentviralload.hashCode() : 0);
        result = 31 * result + (dateresultreceivedfacility != null ? dateresultreceivedfacility.hashCode() : 0);
        result = 31 * result + (alphanumericViralLoadResult != null ? alphanumericViralLoadResult.hashCode() : 0);
        result = 31 * result + (lastdateofsamplecollection != null ? lastdateofsamplecollection.hashCode() : 0);
        result = 31 * result + (viralloadindication != null ? viralloadindication.hashCode() : 0);
        result = 31 * result + (outcomes != null ? outcomes.hashCode() : 0);
        result = 31 * result + (outcomesDate != null ? outcomesDate.hashCode() : 0);
        result = 31 * result + (causeOfDeath != null ? causeOfDeath.hashCode() : 0);
        result = 31 * result + (dateOfIit != null ? dateOfIit.hashCode() : 0);
        result = 31 * result + (iitStatus != null ? iitStatus.hashCode() : 0);
        result = 31 * result + (vaCauseOfDeath != null ? vaCauseOfDeath.hashCode() : 0);
        result = 31 * result + (currentartstatusPharmacy != null ? currentartstatusPharmacy.hashCode() : 0);
        result = 31 * result + (currentartstatus != null ? currentartstatus.hashCode() : 0);
        result = 31 * result + (artstatusPreviousquarter != null ? artstatusPreviousquarter.hashCode() : 0);
        result = 31 * result + (currentartstatusVisit != null ? currentartstatusVisit.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (currentAge != null ? currentAge.hashCode() : 0);
        result = 31 * result + (currentageMonths != null ? currentageMonths.hashCode() : 0);
        result = 31 * result + (ti != null ? ti.hashCode() : 0);
        result = 31 * result + (dateTransferedIn != null ? dateTransferedIn.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (educationallevel != null ? educationallevel.hashCode() : 0);
        result = 31 * result + (maritalstatus != null ? maritalstatus.hashCode() : 0);
        result = 31 * result + (jobstatus != null ? jobstatus.hashCode() : 0);
        result = 31 * result + (phoneno != null ? phoneno.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (stateOfResidence != null ? stateOfResidence.hashCode() : 0);
        result = 31 * result + (lgaOfResidence != null ? lgaOfResidence.hashCode() : 0);
        result = 31 * result + (lastweight != null ? lastweight.hashCode() : 0);
        result = 31 * result + (lastweightdate != null ? lastweightdate.hashCode() : 0);
        result = 31 * result + (bp != null ? bp.hashCode() : 0);
        result = 31 * result + (whostage != null ? whostage.hashCode() : 0);
        result = 31 * result + (firsttldPickup != null ? firsttldPickup.hashCode() : 0);
        result = 31 * result + (dateoffirsttldPickup != null ? dateoffirsttldPickup.hashCode() : 0);
        result = 31 * result + (firstcd4 != null ? firstcd4.hashCode() : 0);
        result = 31 * result + (firstcd4Date != null ? firstcd4Date.hashCode() : 0);
        result = 31 * result + (indicationAhd != null ? indicationAhd.hashCode() : 0);
        result = 31 * result + (cd4LfaResult != null ? cd4LfaResult.hashCode() : 0);
        result = 31 * result + (otherTestTbLamLfLamEtc != null ? otherTestTbLamLfLamEtc.hashCode() : 0);
        result = 31 * result + (serologyForCragResult != null ? serologyForCragResult.hashCode() : 0);
        result = 31 * result + (csfForCragResult != null ? csfForCragResult.hashCode() : 0);
        result = 31 * result + (estimatednextappointmentpharmacy != null ? estimatednextappointmentpharmacy.hashCode() : 0);
        result = 31 * result + (nextApByPharmpill != null ? nextApByPharmpill.hashCode() : 0);
        result = 31 * result + (nextApByCarecard != null ? nextApByCarecard.hashCode() : 0);
        result = 31 * result + (daysToSchedule != null ? daysToSchedule.hashCode() : 0);
        result = 31 * result + (appointmentStatus != null ? appointmentStatus.hashCode() : 0);
        result = 31 * result + (firstInhPickupdate != null ? firstInhPickupdate.hashCode() : 0);
        result = 31 * result + (lastInhPickupdate != null ? lastInhPickupdate.hashCode() : 0);
        result = 31 * result + (currentinhreceived != null ? currentinhreceived.hashCode() : 0);
        result = 31 * result + (currentTbStatus != null ? currentTbStatus.hashCode() : 0);
        result = 31 * result + (dateofcurrentTbstatus != null ? dateofcurrentTbstatus.hashCode() : 0);
        result = 31 * result + (tbtreatmentstartdate != null ? tbtreatmentstartdate.hashCode() : 0);
        result = 31 * result + (tbtreatmentstopdate != null ? tbtreatmentstopdate.hashCode() : 0);
        result = 31 * result + (pbs != null ? pbs.hashCode() : 0);
        result = 31 * result + (pbsdatecreated != null ? pbsdatecreated.hashCode() : 0);
        result = 31 * result + (iptScreeningDate != null ? iptScreeningDate.hashCode() : 0);
        result = 31 * result + (areYouCoughingCurrently != null ? areYouCoughingCurrently.hashCode() : 0);
        result = 31 * result + (doYouHaveFever != null ? doYouHaveFever.hashCode() : 0);
        result = 31 * result + (areYouLosingWeight != null ? areYouLosingWeight.hashCode() : 0);
        result = 31 * result + (areYouHavingNightSweats != null ? areYouHavingNightSweats.hashCode() : 0);
        result = 31 * result + (historyOfContactsWithTbPatients != null ? historyOfContactsWithTbPatients.hashCode() : 0);
        result = 31 * result + (sputumAfb != null ? sputumAfb.hashCode() : 0);
        result = 31 * result + (sputumAfbResult != null ? sputumAfbResult.hashCode() : 0);
        result = 31 * result + (genexpert != null ? genexpert.hashCode() : 0);
        result = 31 * result + (genexpertResult != null ? genexpertResult.hashCode() : 0);
        result = 31 * result + (chestXray != null ? chestXray.hashCode() : 0);
        result = 31 * result + (chestXrayResult != null ? chestXrayResult.hashCode() : 0);
        result = 31 * result + (culture != null ? culture.hashCode() : 0);
        result = 31 * result + (cultureResult != null ? cultureResult.hashCode() : 0);
        result = 31 * result + (isPatientEligibleForIpt != null ? isPatientEligibleForIpt.hashCode() : 0);
        result = 31 * result + (dateEnrolledIntoOtz != null ? dateEnrolledIntoOtz.hashCode() : 0);
        result = 31 * result + (transitionedAdultClinic != null ? transitionedAdultClinic.hashCode() : 0);
        result = 31 * result + (otzOutcome != null ? otzOutcome.hashCode() : 0);
        result = 31 * result + (otzOutcomeDate != null ? otzOutcomeDate.hashCode() : 0);
        result = 31 * result + (positiveLiving != null ? positiveLiving.hashCode() : 0);
        result = 31 * result + (treatmentLiteracy != null ? treatmentLiteracy.hashCode() : 0);
        result = 31 * result + (adolescentsParticipation != null ? adolescentsParticipation.hashCode() : 0);
        result = 31 * result + (leadershipTraining != null ? leadershipTraining.hashCode() : 0);
        result = 31 * result + (peerToPeerMentoship != null ? peerToPeerMentoship.hashCode() : 0);
        result = 31 * result + (roleOfOtz != null ? roleOfOtz.hashCode() : 0);
        result = 31 * result + (otzChampionOreintation != null ? otzChampionOreintation.hashCode() : 0);
        result = 31 * result + (nextVisitDate != null ? nextVisitDate.hashCode() : 0);
        result = 31 * result + (dateGenerated != null ? dateGenerated.hashCode() : 0);
        result = 31 * result + (uniqueId != null ? uniqueId.hashCode() : 0);
        result = 31 * result + (recapture != null ? recapture.hashCode() : 0);
        result = 31 * result + (dateOfRecapture != null ? dateOfRecapture.hashCode() : 0);
        result = 31 * result + (recaptureCount != null ? recaptureCount.hashCode() : 0);
        result = 31 * result + (ageBand != null ? ageBand.hashCode() : 0);
        result = 31 * result + (iitDate != null ? iitDate.hashCode() : 0);
        result = 31 * result + (fy23Q4 != null ? fy23Q4.hashCode() : 0);
        result = 31 * result + (iitStatus2 != null ? iitStatus2.hashCode() : 0);
        result = 31 * result + (iitDate2 != null ? iitDate2.hashCode() : 0);
        result = 31 * result + (rtt != null ? rtt.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (calDaysArt != null ? calDaysArt.hashCode() : 0);
        result = 31 * result + (fy24Q1 != null ? fy24Q1.hashCode() : 0);
        return result;
    }
}
