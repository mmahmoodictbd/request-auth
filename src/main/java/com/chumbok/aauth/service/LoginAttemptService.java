package com.chumbok.aauth.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 3;
    private final String LOGIN_ATTEMPT_PREFIX = "LoginAttemptCounter-IP-";
    private final int EXPIRE_TIME_SECOND = 60;

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    private ValueOperations<String, Long> valOps;

    @PostConstruct
    private void init() {
        valOps = redisTemplate.opsForValue();
    }

    public void loginSucceeded(String remoteIP) {
        valOps.getOperations().delete(LOGIN_ATTEMPT_PREFIX + remoteIP);
    }

    public void loginFailed(String remoteIP) {
        valOps.increment(LOGIN_ATTEMPT_PREFIX + remoteIP, 1L);
    }

    public boolean isBlocked(String remoteIP) {

        Long failedAttempt = getAndCreate(LOGIN_ATTEMPT_PREFIX + remoteIP);

        if (failedAttempt >= MAX_ATTEMPT) {
            return true;
        } else {
            return false;
        }

    }

    private Long getAndCreate(String key) {
        Long val = counter.get(key);
        if (val == null) {
            return counter.increment(key, 0L);
        }
        return counter.get(key);
    }
}
