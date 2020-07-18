package com.javbus.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
    
//    @Bean
//    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
//        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setConnectionFactory(connectionFactory);
//        return redisTemplate;
//    }
    
    /**
     * 自定义 redisTemplate （方法名一定要叫 redisTemplate 因为 @Bean 是根据方法名配置这个bean的name的）
     * 默认的 RedisTemplate<K,V> 为泛型，使用时不太方便，自定义为 <String, Object>
     * 默认序列化方式为 JdkSerializationRedisSerializer 序列化后的内容不方便阅读，改为序列化成 json
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "accountRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    	RedisTemplate<String, Object> template = new RedisTemplate<String, Object>(); // 创建自定义RedisTemplate
    	template.setConnectionFactory(factory);
        setSerializer(template);// 设置序列化工具
        template.afterPropertiesSet();
        return template;
    }
    
    private void setSerializer(RedisTemplate<String, Object> template) {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        // 配置 json 序列化器 - Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        
        // 将 value 序列化成 json
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // 将key序列化成string 以免出现乱码
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
    }
}
