package com.javbus.server.dao;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserRegisterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9084807166243211437L;

	@NotEmpty(message = "账号不能为空!")
	@Length(max = 50, message = "账号长度不能超过50")
	private String account;

	@NotEmpty(message = "密码不能为空!")
	@Length(min = 8, max = 100, message = "密码长度不符合规范")
    private String password;

	@NotEmpty(message = "用户名不能为空!")
	@Length(max = 50, message = "用户名度不能超过50")
    private String userName;

	@NotEmpty(message = "证件号不能为空!")
	@Length(max = 50, message = "证件号长度不能超过50")
    private String idCard;

	@Length(max = 20, message = "电话长度不能超过20")
    private String phone;

	@Length(max = 50, message = "邮箱长度不能超过50")
    private String email;

	@Length(max = 255, message = "地址长度不能超过1255")
    private String address;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
