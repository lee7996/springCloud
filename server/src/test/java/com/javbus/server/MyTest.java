package com.javbus.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.javbus.server.dao.entity.Account;
import com.javbus.server.utils.RedisCache;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

	@Autowired
	private RedisTemplate<String, Object> accountRedisTemplate;
	
	@Autowired
	RedisCache redisUtil;
	@Test
	public void redisTest() {
		Account account = new Account();
		account.setAccount("testAccount");
		account.setAddress("china");
		account.setPhone("110");
		redisUtil.add("account14", account);
//		accountRedisTemplate.opsForValue().set("account13", account);
//		Account accountOut = (Account)accountRedisTemplate.opsForValue().get("account12");
//		System.out.println(accountOut);
//		stringRedisTemplate.opsForValue().set
	}
}
