package com.ihvncr.ihvncrapi.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	private int httpStatusCode;
	private HttpStatus httpStatus;

//	public ErrorDetails(Date timestamp, String message, String details) {
//		this.timestamp = timestamp;
//		this.message = message;
//		this.details = details;
//	}


	public ErrorDetails(Date timestamp, String message, String details, int httpStatusCode, HttpStatus httpStatus) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.httpStatusCode = httpStatusCode;
		this.httpStatus = httpStatus;
	}
}
