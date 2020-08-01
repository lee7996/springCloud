package com.javbus.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

@Component
public class RedisLockConfig {
	// 另参考redisson(更优实现)
//	@Bean(destroyMethod = "close")
	public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisconnectfacFactory) {
		return new RedisLockRegistry(redisconnectfacFactory, "lock"); // 可再传入一个参数——失效时间，单位毫秒，默认60000毫秒
	}
}
