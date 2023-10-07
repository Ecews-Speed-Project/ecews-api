package com.ihvncr.ihvncrapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.zip.ZipException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex) {
		return createHttpResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex, WebRequest request) {
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException ex) {
		return createHttpResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<ErrorDetails> handleDisabledException(DisabledException ex) {
		return createHttpResponse(HttpStatus.FORBIDDEN, ex.getMessage());
	}

	@ExceptionHandler(ZipException.class)
	public ResponseEntity<ErrorDetails> handleZipException(ZipException ex) {
		return createHttpResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
	}

	@ExceptionHandler(FacilityAlreadyExistException.class)
	public ResponseEntity<ErrorDetails> handleFacilityAlreadyExistException(FacilityAlreadyExistException ex) {
		return createHttpResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
	}

	private ResponseEntity<ErrorDetails> createHttpResponse (HttpStatus httpStatus, String message) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), message, httpStatus.getReasonPhrase(), httpStatus.value(), httpStatus);
		return new ResponseEntity<>(errorDetails, httpStatus);
	}
}
