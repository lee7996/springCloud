package com.javbus.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1946600049299901062L;

	private String responseCode;
	private String responseMessage;
	private Object[] args;
	
	public ResponseException(String responseCode, String responseMessage, Object[] args) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.args = args;
	}
	
	public ResponseException(String responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
}
