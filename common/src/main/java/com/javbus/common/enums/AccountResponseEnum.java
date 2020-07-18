package com.javbus.common.enums;

public enum AccountResponseEnum {

	REGISTER_FAIL("100000", "注册失败"),
	REGISTER_KEYS_FAILURE("1000001", "密钥已失效，请重新获取新的密钥");
	
	private final String code;
	private final String message;
	
	AccountResponseEnum(String code, String message) {
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
