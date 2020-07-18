package com.javbus.common.enums;

public enum ResponseEnum {

	RESPONSE_SUCCESS("000000", "成功"),
	SYSTEM_ERROR("999999", "系统错误");
	
	private final String code;
	private final String message;
	
	ResponseEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	
}
