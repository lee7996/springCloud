package com.javbus.common.enums;

public enum RedisKeyEnum {
	
	VERIFICATION_CODE_ERROR_COUNT_ACCOUNT_REGISTER("COR_ACCOUNT_REGISTER:VERIFICATION_CODE_ERROR_COUNT", "短信验证码输入错误次数"),
	VERIFICATION_CODE_ACCOUNT_REGISTER("COR_ACCOUNT_REGISTER:VERIFICATION_CODE", "短信验证码"),
	REGISTER_PUBLIC_PRIVATE_KEY("COR_ACCOUNT_REGISTER:REGISTER_PUBLIC_PRIVATE_KEY", "注册用公私钥");
	
	private final String key;
	private final String desc;
	
	RedisKeyEnum(String key, String desc) {
		this.key = key;
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}
}
