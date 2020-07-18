package com.javbus.server.dao.common;

import java.io.Serializable;

import com.javbus.common.enums.ResponseEnum;

import lombok.Data;

@Data
public class ReturnData<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8159564831890069628L;

	private String responseCode;
	
	private String responseMsg;
	
	private T datas;
	
	
	public ReturnData() {
		this.responseCode = ResponseEnum.RESPONSE_SUCCESS.getCode();
		this.responseMsg = ResponseEnum.RESPONSE_SUCCESS.getMessage();
	}
	
	public ReturnData(String responseCode, String responseMsg) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	}

	public ReturnData(String responseCode, String responseMsg, String... args) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
		for (int i = 0; i < args.length; i++) {
			responseMsg = responseMsg.replace("{" + i + "}", args[i]);
		}
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	}
	
	public static ReturnData<?> getDefaultError() {
		ReturnData<?> returnData = new ReturnData();
		returnData.setResponseCode(ResponseEnum.SYSTEM_ERROR.getCode());
		returnData.setResponseMsg(ResponseEnum.SYSTEM_ERROR.getMessage());
		return returnData;
	}
}
