package com.javbus.common.enums;

public enum VerificationCodeResponseCodeEnum {
	
	VERIFICATION_CODE_ERROR_TOPLIMIT("610001", "验证码错误次数达到上限，请重新发送验证码"),
	VERIFICATION_CODE_FAILURE("610002", "验证码已失效，请重新发送验证码");
	
	private final String code;
	private final String message;
	
	VerificationCodeResponseCodeEnum(String code, String message) {
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
