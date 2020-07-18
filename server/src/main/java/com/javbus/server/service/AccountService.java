package com.javbus.server.service;

import com.javbus.server.dao.UserRegisterDTO;
import com.javbus.server.dao.entity.Account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.validation.Valid;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lee
 * @since 2020-04-11
 */
public interface AccountService extends IService<Account> {

	void register(@Valid UserRegisterDTO userRegister) throws NoSuchAlgorithmException, InvalidKeySpecException;

}
