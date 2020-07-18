package com.javbus.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javbus.server.dao.common.ReturnData;

@RestController
@RequestMapping
public class ReadyDoConroller {
	@Autowired
	StringRedisTemplate stringRedisTemplate;
		@GetMapping("/readyDo")
	public ReturnData<?> readyDo() {
		stringRedisTemplate.opsForValue().set("SHORT_MESSAGE_KEY", "5");
		return null;
	}
}
