package com.javbus.server.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javbus.common.dto.ResaKeys;
import com.javbus.common.enums.AccountResponseEnum;
import com.javbus.common.exception.ResponseException;
import com.javbus.common.utils.RSAUtil;
import com.javbus.server.dao.UserRegisterDTO;
import com.javbus.server.dao.entity.Account;
import com.javbus.server.mapper.AccountMapper;
import com.javbus.server.service.AccountService;
import com.javbus.server.utils.EncryptionUtils;
import com.javbus.server.utils.RedisCache;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lee
 * @since 2020-04-11
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	RedisCache<String> stringRedisCache;

	@Override
	public void register(@Valid UserRegisterDTO userRegister) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 获取密钥对
		String json = stringRedisCache.get("REGISTER_PUBLIC_PRIVATE_KEY");
		ResaKeys keys = JSONObject.parseObject(json, ResaKeys.class);
		if (null == keys) {
			throw new ResponseException(AccountResponseEnum.REGISTER_KEYS_FAILURE.getCode(), AccountResponseEnum.REGISTER_KEYS_FAILURE.getMessage()); 
		}
		String pwd = null;
		try {
			// 解密密码
			RSAPrivateKey privateKey = RSAUtil.getPrivateKey(keys.getPrivateKey());
			pwd = RSAUtil.privateDecrypt(userRegister.getPassword(), privateKey);
		} catch (Exception e) {
			log.error("注册账号——密码解密异常：", e);
			// TODO throw exception
		}
		// TODO 校验密码是否符合规则
		
		// 储存账号信息
		Account account = new Account();
		BeanUtils.copyProperties(userRegister, account);
		if (StringUtils.isNotEmpty(userRegister.getPassword())) {
			String salt = EncryptionUtils.generateSalt();
			String password = EncryptionUtils.accountEncode(userRegister.getPassword(), salt);
			log.info(">>>>>>>>>>>>>>>>>>>>加密后密码: {}", password);
			account.setPassword(password);
			account.setSalt(salt);
		}
		accountMapper.insert(account);
		
		// 储存成功创建session
	}
	
	public void test() {
		System.out.println("test");
	}

}
