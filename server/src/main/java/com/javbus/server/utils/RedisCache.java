package com.javbus.server.utils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCache<V> {

	@Resource
	private RedisTemplate<String, V> accountRedisTemplate;
	
	public void add(String key, V object) {
		accountRedisTemplate.opsForValue().set(key, object);
	}
	
	public void add(String key, V object, long timeOut) {
		accountRedisTemplate.opsForValue().set(key, object, timeOut, TimeUnit.SECONDS);
	}
	
	public V get(String key) {
		return accountRedisTemplate.opsForValue().get(key);
	}
	
	public boolean delete(String key) {
		return accountRedisTemplate.delete(key);
	}
	
	/**
	 * 批量删除
	 * @param keyPrefix
	 * @return
	 */
	public Long batchDelete(String keyPrefix) {
		Set<String> keys = accountRedisTemplate.keys(keyPrefix + "*");
		return accountRedisTemplate.delete(keys);
	}
}
