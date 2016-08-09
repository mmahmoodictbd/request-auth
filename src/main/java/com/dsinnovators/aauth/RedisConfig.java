package com.dsinnovators.aauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setPassword("12345678");
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.afterPropertiesSet();
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, Long> redisTemplate() {
		RedisTemplate<String, Long> template = new RedisTemplate<String, Long>();
		template.setConnectionFactory(redisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer((new GenericToStringSerializer<Long>(Long.class)));
		return template;
	}
}
