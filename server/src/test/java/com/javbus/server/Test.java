package com.javbus.server;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.javbus.server.service.AccountService;
import com.javbus.server.service.impl.AccountServiceImpl;

public class Test {

	public static void main(String[] args) {
		String str = "";
		System.out.println(StringUtils.isEmpty(str));
		AccountService as = new AccountServiceImpl();
		AccountServiceImpl as2 = new AccountServiceImpl();
	}
}
