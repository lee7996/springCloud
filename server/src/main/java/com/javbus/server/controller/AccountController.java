package com.javbus.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.javbus.common.enums.AccountResponseEnum;
import com.javbus.common.enums.RedisKeyEnum;
import com.javbus.common.enums.ResponseEnum;
import com.javbus.common.enums.VerificationCodeResponseCodeEnum;
import com.javbus.server.dao.UserRegisterDTO;
import com.javbus.server.dao.common.ReturnData;
import com.javbus.server.service.AccountService;
import com.javbus.server.utils.RedisCache;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class AccountController {

	@Autowired
	AccountService accountService;
	
//	@Autowired
//	StringRedisTemplate stringRedisTemplate;
	@Autowired
	RedisCache<String> stringRedisCache;
	
	// 验证码失败次数缓存时间
	private static final Integer VERIFICATION_CODE_ERROR_COUNT_EXPIRE_TIMES = 300;
	// 验证码缓存时间
	private static final Integer  VERIFICATION_CODE_EXPIRE_TIMES = 180;
	
	@PostMapping("/register")
	public ReturnData<?> register(@RequestBody @Valid UserRegisterDTO userRegister) {
		log.info(">>>>>>>>>>>>>>>>>> 用户注册: {}", userRegister);
		ReturnData response = new ReturnData<>();
		try {

			// 校验验证码输入失败次数
			String shortMsgErrorCount = stringRedisCache.get(RedisKeyEnum.VERIFICATION_CODE_ERROR_COUNT_ACCOUNT_REGISTER.getKey() + userRegister.getPhone());
			if (!StringUtils.isEmpty(shortMsgErrorCount) && Integer.valueOf(shortMsgErrorCount) > 5) {
				response.setResponseCode(VerificationCodeResponseCodeEnum.VERIFICATION_CODE_ERROR_TOPLIMIT.getCode());
				response.setResponseMsg(VerificationCodeResponseCodeEnum.VERIFICATION_CODE_ERROR_TOPLIMIT.getMessage());
				return response;
			}
			
			// TODO 校验验证码
			ReturnData checkResult = checkVerificationCode(userRegister);
			if (!ResponseEnum.RESPONSE_SUCCESS.getCode().equals(checkResult.getResponseCode())) {
				if (StringUtils.isEmpty(shortMsgErrorCount)) {
					stringRedisCache.add(RedisKeyEnum.VERIFICATION_CODE_ERROR_COUNT_ACCOUNT_REGISTER.getKey() + userRegister.getPhone(), "1", VERIFICATION_CODE_ERROR_COUNT_EXPIRE_TIMES);
				} else {
					stringRedisCache.add(RedisKeyEnum.VERIFICATION_CODE_ERROR_COUNT_ACCOUNT_REGISTER.getKey() + userRegister.getPhone(), (Integer.valueOf(shortMsgErrorCount) + 1) + "", VERIFICATION_CODE_ERROR_COUNT_EXPIRE_TIMES);
				}
				response.setResponseCode(checkResult.getResponseCode());
				response.setResponseCode(checkResult.getResponseMsg());
				return response;
			}
			
			// 查看验证码是否失效
			String verificationCode = stringRedisCache.get(RedisKeyEnum.VERIFICATION_CODE_ACCOUNT_REGISTER.getKey() + userRegister.getPhone());
			if (StringUtils.isEmpty(verificationCode)) {
				response.setResponseCode(VerificationCodeResponseCodeEnum.VERIFICATION_CODE_FAILURE.getCode());
				response.setResponseMsg(VerificationCodeResponseCodeEnum.VERIFICATION_CODE_FAILURE.getMessage());
				return response;
			}
			accountService.register(userRegister);
		} catch (Exception e) {
			log.error("注册失败： ", e);
			response.setResponseCode(AccountResponseEnum.REGISTER_FAIL.getCode());
			response.setResponseMsg(AccountResponseEnum.REGISTER_FAIL.getMessage());
		}
		return response;
	}

	private ReturnData checkVerificationCode(@Valid UserRegisterDTO userRegister) {
		// TODO 调用第三方服务，校验验证码
		
		// 校验成功，将验证码存入redis，此处存入手机号
		stringRedisCache.add(RedisKeyEnum.VERIFICATION_CODE_ACCOUNT_REGISTER.getKey() + userRegister.getPhone(), userRegister.getPhone(), VERIFICATION_CODE_EXPIRE_TIMES);
		
		return new ReturnData();
	}
	
	@GetMapping("/test/{str}")
	public ReturnData htmlTest(@PathVariable(name = "str") String str) {
		ReturnData result = new ReturnData();
		String newStr = StringEscapeUtils.escapeHtml(str);
		System.out.println(newStr);
		Map<String, Object> map = new HashMap<>();
		map.put("String", newStr);
		result.setDatas(map);
		return result;
		
	}
	
	@PostMapping("/test2")
	public ReturnData htmlTest2(@RequestBody JSONObject str) {
		ReturnData result = new ReturnData();
		String newStr = StringEscapeUtils.escapeHtml(str.getString("str"));
		System.out.println(newStr);
		Map<String, Object> map = new HashMap<>();
		map.put("String", newStr);
		result.setDatas(map);
		return result;
		
	}
	
	@PostMapping("/test1")
	public String htmlTest1(@RequestBody JSONObject str) {
		ReturnData result = new ReturnData();
		String newStr = StringEscapeUtils.escapeHtml(str.getString("str"));
		System.out.println(str.getString("str"));
		return str.getString("str");
	}
}
