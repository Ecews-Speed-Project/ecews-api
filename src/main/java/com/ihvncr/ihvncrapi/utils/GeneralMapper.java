/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihvncr.ihvncrapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralMapper {
	
	public static ObjectMapper getCustomObjectMapper() {
		return new ObjectMapper() {

			ObjectMapper mapper = new ObjectMapper();
			
			public String writeValue(Object value) {
				try {
					return mapper.writeValueAsString(value);
				}
				catch (Exception ex) {
					Logger.getLogger(GeneralMapper.class.getName()).log(Level.SEVERE, null, ex);
				}
				return null;
			}
			
			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return mapper.readValue(value, valueType);
				}
				catch (IOException ex) {
					Logger.getLogger(GeneralMapper.class.getName()).log(Level.SEVERE, null, ex);
				}
				return null;
			}
		};
	}
	
	public static ResponseEntity<String> requestPbsInfo(String uuid) throws UnirestException {

		RestTemplate restTemplate = new RestTemplate();
		String PBS_RECAPTURE_URL = "http://localhost:2018/api/FingerPrint/CheckForPreviousCapture?PatientUUID=05a8cf33-aae0-4e02-8ea3-02cfc833807a";
		ResponseEntity<String> response
				= restTemplate.getForEntity(PBS_RECAPTURE_URL, String.class);

		return response;
	}

}
