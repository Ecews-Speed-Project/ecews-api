/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package com.ihvncr.ihvncrapi.model;

import java.sql.Blob;

public class BiometricInfo {
	
	private Integer biometricInfoId;
	
	private Integer patientId;
	
	private String template;
	
	private Blob newTemplate;
	
	private Integer imageWidth;
	
	private Integer imageHeight;
	
	private Integer imageDPI;
	
	private Integer imageQuality;
	
	private String fingerPosition;
	
	private String serialNumber;
	
	private String model;
	
	private String manufacturer;
	
	public String qualityFlag;
	
	public String getQualityFlag() {
		return qualityFlag;
	}
	
	public void setQualityFlag(String qualityFlag) {
		this.qualityFlag = qualityFlag;
	}
	
	private Integer creator;
	
	private String dateCreated;
	
	public Integer getBiometricInfoId() {
		return biometricInfoId;
	}
	
	public void setBiometricInfoId(Integer biometricInfoId) {
		this.biometricInfoId = biometricInfoId;
	}
	
	public Integer getPatientId() {
		return patientId;
	}
	
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	public String getTemplate() {
		if (getNewTemplate() != null) {
			Blob blob = getNewTemplate();
			try {
				byte[] blobData = blob.getBytes(1, (int) blob.length());
				setNewTemplate(null);
				return new String(blobData);
			}
			catch (Exception ex) {}
		}
		return template;
	}
	
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public Integer getImageWidth() {
		return imageWidth;
	}
	
	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}
	
	public Integer getImageHeight() {
		return imageHeight;
	}
	
	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}
	
	public Integer getImageDPI() {
		return imageDPI;
	}
	
	public void setImageDPI(Integer imageDPI) {
		this.imageDPI = imageDPI;
	}
	
	public Integer getImageQuality() {
		return imageQuality;
	}
	
	public void setImageQuality(Integer imageQuality) {
		this.imageQuality = imageQuality;
	}
	
	public String getFingerPosition() {
		return fingerPosition;
	}
	
	public void setFingerPosition(String fingerPosition) {
		this.fingerPosition = fingerPosition;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public Integer getCreator() {
		return creator;
	}
	
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
	public String getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Blob getNewTemplate() {
		return newTemplate;
	}
	
	public void setNewTemplate(Blob newTemplate) {
		this.newTemplate = newTemplate;
	}
}
