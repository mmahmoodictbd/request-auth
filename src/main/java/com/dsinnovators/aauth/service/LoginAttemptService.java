package com.dsinnovators.aauth.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

	private final int MAX_ATTEMPT = 3;
	private final String LOGIN_ATTEMPT_PREFIX = "LoginAttempt-IP-";

	@SuppressWarnings("unused")
	private final int EXPIRE_TIME_SECOND = 300;

	@Autowired
	private RedisTemplate<String, Long> redisTemplate;

	private ValueOperations<String, Long> counter;

	@PostConstruct
	private void init() {
		counter = redisTemplate.opsForValue();
	}

	public void loginSucceeded(String remoteIp) {
		counter.getOperations().delete(LOGIN_ATTEMPT_PREFIX + remoteIp);
	}

	public void loginFailed(String remoteIp) {
		counter.increment(LOGIN_ATTEMPT_PREFIX + remoteIp, 1);
	}

	public boolean isBlocked(String remoteIp) {

		Long failedAttempt = counter.get(LOGIN_ATTEMPT_PREFIX + remoteIp);

		if (failedAttempt > MAX_ATTEMPT) {
			return true;
		} else {
			return false;
		}

	}
}
