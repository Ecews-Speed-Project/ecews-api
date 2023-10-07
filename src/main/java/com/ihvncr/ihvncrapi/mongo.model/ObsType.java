package com.ihvncr.ihvncrapi.mongo.model;//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.06 at 02:19:42 AM WAT 
//


import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>Java class for ObsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ObsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="obs_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="obs_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="person_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="concept_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="encounter_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="form_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pmm_form" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="encounter_type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="obs_datetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="location_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="obs_group_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="value_coded" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="value_datetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="value_numeric" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="value_text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="date_created" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="voided" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="voided_by" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="date_voided" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="variable_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="variable_value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datim_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="patient_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="encounter_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="visit_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datatype" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObsType", propOrder = {
        "obsUuid",
        "obsId",
        "personId",
        "conceptId",
        "encounterId",
        "formId",
        "pmmForm",
        "encounterType",
        "obsDatetime",
        "locationId",
        "obsGroupId",
        "valueCoded",
        "valueDatetime",
        "valueNumeric",
        "valueText",
        "creator",
        "dateCreated",
        "voided",
        "voidedBy",
        "dateVoided",
        "variableName",
        "variableValue",
        "datimId",
        "patientUuid",
        "encounterUuid",
        "visitUuid",
        "datatype"
})
@ToString
public class ObsType implements Serializable {

    @XmlElement(name = "obs_uuid", required = true)
    protected String obsUuid;
    @XmlElement(name = "obs_id")
    protected int obsId;
    @XmlElement(name = "person_id")
    protected int personId;
    @XmlElement(name = "concept_id")
    protected int conceptId;
    @XmlElement(name = "encounter_id")
    protected int encounterId;
    @XmlElement(name = "form_id")
    protected int formId;
    @XmlElement(name = "pmm_form", required = true)
    protected String pmmForm;
    @XmlElement(name = "encounter_type")
    protected int encounterType;
    @XmlElement(name = "obs_datetime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date obsDatetime;
    @XmlElement(name = "location_id")
    protected int locationId;
    @XmlElement(name = "obs_group_id")
    protected int obsGroupId;
    @XmlElement(name = "value_coded")
    protected int valueCoded;
    @XmlElement(name = "value_datetime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date valueDatetime;
    @XmlElement(name = "value_numeric", required = true)
    protected BigDecimal valueNumeric;
    @XmlElement(name = "value_text", required = true)
    protected String valueText;
    protected int creator;
    @XmlElement(name = "date_created", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date dateCreated;
    protected int voided;
    @XmlElement(name = "voided_by")
    protected int voidedBy;
    @XmlElement(name = "date_voided", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date dateVoided;
    @XmlElement(name = "variable_name", required = true)
    protected String variableName;
    @XmlElement(name = "variable_value", required = true)
    protected String variableValue;
    @XmlElement(name = "datim_id", required = true)
    protected String datimId;
    @XmlElement(name = "patient_uuid", required = true)
    protected String patientUuid;
    @XmlElement(name = "encounter_uuid", required = true)
    protected String encounterUuid;
    @XmlElement(name = "visit_uuid", required = true)
    protected String visitUuid;
    protected int datatype;

    /**
     * Gets the value of the obsUuid property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getObsUuid() {
        return obsUuid;
    }

    /**
     * Sets the value of the obsUuid property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setObsUuid(String value) {
        this.obsUuid = value;
    }

    /**
     * Gets the value of the obsId property.
     */
    public int getObsId() {
        return obsId;
    }

    /**
     * Sets the value of the obsId property.
     */
    public void setObsId(int value) {
        this.obsId = value;
    }

    /**
     * Gets the value of the personId property.
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Sets the value of the personId property.
     */
    public void setPersonId(int value) {
        this.personId = value;
    }

    /**
     * Gets the value of the conceptId property.
     */
    public int getConceptId() {
        return conceptId;
    }

    /**
     * Sets the value of the conceptId property.
     */
    public void setConceptId(int value) {
        this.conceptId = value;
    }

    /**
     * Gets the value of the encounterId property.
     */
    public int getEncounterId() {
        return encounterId;
    }

    /**
     * Sets the value of the encounterId property.
     */
    public void setEncounterId(int value) {
        this.encounterId = value;
    }

    /**
     * Gets the value of the formId property.
     */
    public int getFormId() {
        return formId;
    }

    /**
     * Sets the value of the formId property.
     */
    public void setFormId(int value) {
        this.formId = value;
    }

    /**
     * Gets the value of the pmmForm property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPmmForm() {
        return pmmForm;
    }

    /**
     * Sets the value of the pmmForm property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPmmForm(String value) {
        this.pmmForm = value;
    }

    /**
     * Gets the value of the encounterType property.
     */
    public int getEncounterType() {
        return encounterType;
    }

    /**
     * Sets the value of the encounterType property.
     */
    public void setEncounterType(int value) {
        this.encounterType = value;
    }

    /**
     * Gets the value of the obsDatetime property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getObsDatetime() {
        return obsDatetime;
    }

    /**
     * Sets the value of the obsDatetime property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setObsDatetime(Date value) {
        this.obsDatetime = value;
    }

    /**
     * Gets the value of the locationId property.
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Sets the value of the locationId property.
     */
    public void setLocationId(int value) {
        this.locationId = value;
    }

    /**
     * Gets the value of the obsGroupId property.
     */
    public int getObsGroupId() {
        return obsGroupId;
    }

    /**
     * Sets the value of the obsGroupId property.
     */
    public void setObsGroupId(int value) {
        this.obsGroupId = value;
    }

    /**
     * Gets the value of the valueCoded property.
     */
    public int getValueCoded() {
        return valueCoded;
    }

    /**
     * Sets the value of the valueCoded property.
     */
    public void setValueCoded(int value) {
        this.valueCoded = value;
    }

    /**
     * Gets the value of the valueDatetime property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getValueDatetime() {
        return valueDatetime;
    }

    /**
     * Sets the value of the valueDatetime property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setValueDatetime(Date value) {
        this.valueDatetime = value;
    }

    /**
     * Gets the value of the valueNumeric property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getValueNumeric() {
        return valueNumeric;
    }

    /**
     * Sets the value of the valueNumeric property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setValueNumeric(BigDecimal value) {
        this.valueNumeric = value;
    }

    /**
     * Gets the value of the valueText property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValueText() {
        return valueText;
    }

    /**
     * Sets the value of the valueText property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValueText(String value) {
        this.valueText = value;
    }

    /**
     * Gets the value of the creator property.
     */
    public int getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     */
    public void setCreator(int value) {
        this.creator = value;
    }

    /**
     * Gets the value of the dateCreated property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setDateCreated(Date value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the voided property.
     */
    public int getVoided() {
        return voided;
    }

    /**
     * Sets the value of the voided property.
     */
    public void setVoided(int value) {
        this.voided = value;
    }

    /**
     * Gets the value of the voidedBy property.
     */
    public int getVoidedBy() {
        return voidedBy;
    }

    /**
     * Sets the value of the voidedBy property.
     */
    public void setVoidedBy(int value) {
        this.voidedBy = value;
    }

    /**
     * Gets the value of the dateVoided property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getDateVoided() {
        return dateVoided;
    }

    /**
     * Sets the value of the dateVoided property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setDateVoided(Date value) {
        this.dateVoided = value;
    }

    /**
     * Gets the value of the variableName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Sets the value of the variableName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVariableName(String value) {
        this.variableName = value;
    }

    /**
     * Gets the value of the variableValue property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVariableValue() {
        return variableValue;
    }

    /**
     * Sets the value of the variableValue property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVariableValue(String value) {
        this.variableValue = value;
    }

    /**
     * Gets the value of the datimId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDatimId() {
        return datimId;
    }

    /**
     * Sets the value of the datimId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDatimId(String value) {
        this.datimId = value;
    }

    /**
     * Gets the value of the patientUuid property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPatientUuid() {
        return patientUuid;
    }

    /**
     * Sets the value of the patientUuid property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPatientUuid(String value) {
        this.patientUuid = value;
    }

    /**
     * Gets the value of the encounterUuid property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEncounterUuid() {
        return encounterUuid;
    }

    /**
     * Sets the value of the encounterUuid property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEncounterUuid(String value) {
        this.encounterUuid = value;
    }

    /**
     * Gets the value of the visitUuid property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVisitUuid() {
        return visitUuid;
    }

    /**
     * Sets the value of the visitUuid property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVisitUuid(String value) {
        this.visitUuid = value;
    }

    /**
     * Gets the value of the datatype property.
     */
    public int getDatatype() {
        return datatype;
    }

    /**
     * Sets the value of the datatype property.
     */
    public void setDatatype(int value) {
        this.datatype = value;
    }

}
