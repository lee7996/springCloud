package com.javbus.common.enums;

public enum RSAResponseEnum {

	CREATE_KEYS_ERROR("100300", "创建密钥对失败");
	
	private final String code;
	private final String message;
	
	RSAResponseEnum (String code, String message) {
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
