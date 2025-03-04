package com.ihvncr.ihvncrapi.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "appointment_list", schema = "public", catalog = "ecews")
public class AppointmentList {
    @Basic
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id", nullable = true)
    private Integer id;
    @Basic
    @Column(name = "ip", nullable = true, length = -1)
    private String ip;
    @Basic
    @Column(name = "state", nullable = true, length = -1)
    private String state;
    @Basic
    @Column(name = "lga", nullable = true, length = -1)
    private String lga;
    @Basic
    @Column(name = "datim_code", nullable = true, length = -1)
    private String datimCode;
    @Basic
    @Column(name = "facilityname", nullable = true, length = -1)
    private String facilityname;
    @Basic
    @Column(name = "patient_id", nullable = true, length = -1)
    private String patientId;
    @Basic
    @Column(name = "pepid", nullable = true, length = -1)
    private String pepid;
    @Basic
    @Column(name = "atim_pepid", nullable = true, length = -1)
    private String atimPepid;
    @Basic
    @Column(name = "sex", nullable = true, length = -1)
    private String sex;
    @Basic
    @Column(name = "artstartdate", nullable = true, length = -1)
    private String artstartdate;
    @Basic
    @Column(name = "daysonart", nullable = true, length = -1)
    private String daysonart;
    @Basic
    @Column(name = "pharmacy_lastpickupdate", nullable = true)
    private Date pharmacyLastpickupdate;
    @Basic
    @Column(name = "daysofarvrefill", nullable = true, length = -1)
    private String daysofarvrefill;
    @Basic
    @Column(name = "dp_next_appointment", nullable = true)
    private Date dpNextAppointment;
    @Basic
    @Column(name = "regimenlineatartstart", nullable = true, length = -1)
    private String regimenlineatartstart;
    @Basic
    @Column(name = "regimenatartstart", nullable = true, length = -1)
    private String regimenatartstart;
    @Basic
    @Column(name = "currentregimenline", nullable = true, length = -1)
    private String currentregimenline;
    @Basic
    @Column(name = "currentartregimen", nullable = true, length = -1)
    private String currentartregimen;
    @Basic
    @Column(name = "currentpregnancystatus", nullable = true, length = -1)
    private String currentpregnancystatus;
    @Basic
    @Column(name = "currentviralload", nullable = true, length = -1)
    private String currentviralload;
    @Basic
    @Column(name = "dateofcurrentviralload", nullable = true)
    private Date dateofcurrentviralload;
    @Basic
    @Column(name = "vl_next_appointment_date_cal", nullable = true, length = -1)
    private String vlNextAppointmentDateCal;
    @Basic
    @Column(name = "vl_next_appointment_date", nullable = true)
    private Date vlNextAppointmentDate;
    @Basic
    @Column(name = "currentartstatus_pharmacy", nullable = true, length = -1)
    private String currentartstatusPharmacy;
    @Basic
    @Column(name = "phoneno", nullable = true, length = -1)
    private String phoneno;
    @Basic
    @Column(name = "datim_pepid", nullable = true, length = 50)
    private String datimPepid;
    @Basic
    @Column(name = "bp", nullable = true, length = 50)
    private String bp;
    @Basic
    @Column(name = "weight", nullable = true)
    private Integer weight;
    @Basic
    @Column(name = "height", nullable = true, length = 50)
    private String height;
    @Basic
    @Column(name = "dob", nullable = true)
    private Date dob;
    @Basic
    @Column(name = "current_age", nullable = true)
    private Integer currentAge;
    @Basic
    @Column(name = "qualification", nullable = true, length = 50)
    private String qualification;
    @Basic
    @Column(name = "occupation", nullable = true, length = 50)
    private String occupation;
    @Basic
    @Column(name = "first_cd4", nullable = true, length = 50)
    private String firstCd4;
    @Basic
    @Column(name = "first_cd4_date", nullable = true)
    private Date firstCd4Date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPepid() {
        return pepid;
    }

    public void setPepid(String pepid) {
        this.pepid = pepid;
    }

    public String getAtimPepid() {
        return atimPepid;
    }

    public void setAtimPepid(String atimPepid) {
        this.atimPepid = atimPepid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArtstartdate() {
        return artstartdate;
    }

    public void setArtstartdate(String artstartdate) {
        this.artstartdate = artstartdate;
    }

    public String getDaysonart() {
        return daysonart;
    }

    public void setDaysonart(String daysonart) {
        this.daysonart = daysonart;
    }

    public Date getPharmacyLastpickupdate() {
        return pharmacyLastpickupdate;
    }

    public void setPharmacyLastpickupdate(Date pharmacyLastpickupdate) {
        this.pharmacyLastpickupdate = pharmacyLastpickupdate;
    }

    public String getDaysofarvrefill() {
        return daysofarvrefill;
    }

    public void setDaysofarvrefill(String daysofarvrefill) {
        this.daysofarvrefill = daysofarvrefill;
    }

    public Date getDpNextAppointment() {
        return dpNextAppointment;
    }

    public void setDpNextAppointment(Date dpNextAppointment) {
        this.dpNextAppointment = dpNextAppointment;
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

    public String getCurrentpregnancystatus() {
        return currentpregnancystatus;
    }

    public void setCurrentpregnancystatus(String currentpregnancystatus) {
        this.currentpregnancystatus = currentpregnancystatus;
    }

    public String getCurrentviralload() {
        return currentviralload;
    }

    public void setCurrentviralload(String currentviralload) {
        this.currentviralload = currentviralload;
    }

    public Date getDateofcurrentviralload() {
        return dateofcurrentviralload;
    }

    public void setDateofcurrentviralload(Date dateofcurrentviralload) {
        this.dateofcurrentviralload = dateofcurrentviralload;
    }

    public String getVlNextAppointmentDateCal() {
        return vlNextAppointmentDateCal;
    }

    public void setVlNextAppointmentDateCal(String vlNextAppointmentDateCal) {
        this.vlNextAppointmentDateCal = vlNextAppointmentDateCal;
    }

    public Date getVlNextAppointmentDate() {
        return vlNextAppointmentDate;
    }

    public void setVlNextAppointmentDate(Date vlNextAppointmentDate) {
        this.vlNextAppointmentDate = vlNextAppointmentDate;
    }

    public String getCurrentartstatusPharmacy() {
        return currentartstatusPharmacy;
    }

    public void setCurrentartstatusPharmacy(String currentartstatusPharmacy) {
        this.currentartstatusPharmacy = currentartstatusPharmacy;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppointmentList that = (AppointmentList) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (lga != null ? !lga.equals(that.lga) : that.lga != null) return false;
        if (datimCode != null ? !datimCode.equals(that.datimCode) : that.datimCode != null) return false;
        if (facilityname != null ? !facilityname.equals(that.facilityname) : that.facilityname != null) return false;
        if (patientId != null ? !patientId.equals(that.patientId) : that.patientId != null) return false;
        if (pepid != null ? !pepid.equals(that.pepid) : that.pepid != null) return false;
        if (atimPepid != null ? !atimPepid.equals(that.atimPepid) : that.atimPepid != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (artstartdate != null ? !artstartdate.equals(that.artstartdate) : that.artstartdate != null) return false;
        if (daysonart != null ? !daysonart.equals(that.daysonart) : that.daysonart != null) return false;
        if (pharmacyLastpickupdate != null ? !pharmacyLastpickupdate.equals(that.pharmacyLastpickupdate) : that.pharmacyLastpickupdate != null)
            return false;
        if (daysofarvrefill != null ? !daysofarvrefill.equals(that.daysofarvrefill) : that.daysofarvrefill != null)
            return false;
        if (dpNextAppointment != null ? !dpNextAppointment.equals(that.dpNextAppointment) : that.dpNextAppointment != null)
            return false;
        if (regimenlineatartstart != null ? !regimenlineatartstart.equals(that.regimenlineatartstart) : that.regimenlineatartstart != null)
            return false;
        if (regimenatartstart != null ? !regimenatartstart.equals(that.regimenatartstart) : that.regimenatartstart != null)
            return false;
        if (currentregimenline != null ? !currentregimenline.equals(that.currentregimenline) : that.currentregimenline != null)
            return false;
        if (currentartregimen != null ? !currentartregimen.equals(that.currentartregimen) : that.currentartregimen != null)
            return false;
        if (currentpregnancystatus != null ? !currentpregnancystatus.equals(that.currentpregnancystatus) : that.currentpregnancystatus != null)
            return false;
        if (currentviralload != null ? !currentviralload.equals(that.currentviralload) : that.currentviralload != null)
            return false;
        if (dateofcurrentviralload != null ? !dateofcurrentviralload.equals(that.dateofcurrentviralload) : that.dateofcurrentviralload != null)
            return false;
        if (vlNextAppointmentDateCal != null ? !vlNextAppointmentDateCal.equals(that.vlNextAppointmentDateCal) : that.vlNextAppointmentDateCal != null)
            return false;
        if (vlNextAppointmentDate != null ? !vlNextAppointmentDate.equals(that.vlNextAppointmentDate) : that.vlNextAppointmentDate != null)
            return false;
        if (currentartstatusPharmacy != null ? !currentartstatusPharmacy.equals(that.currentartstatusPharmacy) : that.currentartstatusPharmacy != null)
            return false;
        if (phoneno != null ? !phoneno.equals(that.phoneno) : that.phoneno != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (lga != null ? lga.hashCode() : 0);
        result = 31 * result + (datimCode != null ? datimCode.hashCode() : 0);
        result = 31 * result + (facilityname != null ? facilityname.hashCode() : 0);
        result = 31 * result + (patientId != null ? patientId.hashCode() : 0);
        result = 31 * result + (pepid != null ? pepid.hashCode() : 0);
        result = 31 * result + (atimPepid != null ? atimPepid.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (artstartdate != null ? artstartdate.hashCode() : 0);
        result = 31 * result + (daysonart != null ? daysonart.hashCode() : 0);
        result = 31 * result + (pharmacyLastpickupdate != null ? pharmacyLastpickupdate.hashCode() : 0);
        result = 31 * result + (daysofarvrefill != null ? daysofarvrefill.hashCode() : 0);
        result = 31 * result + (dpNextAppointment != null ? dpNextAppointment.hashCode() : 0);
        result = 31 * result + (regimenlineatartstart != null ? regimenlineatartstart.hashCode() : 0);
        result = 31 * result + (regimenatartstart != null ? regimenatartstart.hashCode() : 0);
        result = 31 * result + (currentregimenline != null ? currentregimenline.hashCode() : 0);
        result = 31 * result + (currentartregimen != null ? currentartregimen.hashCode() : 0);
        result = 31 * result + (currentpregnancystatus != null ? currentpregnancystatus.hashCode() : 0);
        result = 31 * result + (currentviralload != null ? currentviralload.hashCode() : 0);
        result = 31 * result + (dateofcurrentviralload != null ? dateofcurrentviralload.hashCode() : 0);
        result = 31 * result + (vlNextAppointmentDateCal != null ? vlNextAppointmentDateCal.hashCode() : 0);
        result = 31 * result + (vlNextAppointmentDate != null ? vlNextAppointmentDate.hashCode() : 0);
        result = 31 * result + (currentartstatusPharmacy != null ? currentartstatusPharmacy.hashCode() : 0);
        result = 31 * result + (phoneno != null ? phoneno.hashCode() : 0);
        return result;
    }

    public String getDatimPepid() {
        return datimPepid;
    }

    public void setDatimPepid(String datimPepid) {
        this.datimPepid = datimPepid;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(Integer currentAge) {
        this.currentAge = currentAge;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getFirstCd4() {
        return firstCd4;
    }

    public void setFirstCd4(String firstCd4) {
        this.firstCd4 = firstCd4;
    }

    public Date getFirstCd4Date() {
        return firstCd4Date;
    }

    public void setFirstCd4Date(Date firstCd4Date) {
        this.firstCd4Date = firstCd4Date;
    }
}
