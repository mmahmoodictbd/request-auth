package com.chumboknnovators.aauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class RequestRateLimitService {

    // 7 request in 5 second
    private final int MAX_REQUEST = 84;

    private final int EXPIRE_TIME_SECOND = 60;

    private final String REQUEST_STARTDATE_PREFIX = "RequestStartDate-IP-";
    private final String REQUEST_COUNTER_PREFIX = "RequestCounter-IP-";


    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    private ValueOperations<String, Long> valOps;

    @PostConstruct
    private void init() {
        valOps = redisTemplate.opsForValue();
    }

    public boolean isRequestBlocked(String remoteIP) {

        Long reqCount = getAndIncrease(REQUEST_COUNTER_PREFIX + remoteIP);

        if (reqCount >= MAX_REQUEST) {
            return true;
        } else {
            return false;
        }

    }

    private Long getAndIncrease(String key) {

        Long val = valOps.get(key);
        if (val == null) {
            valOps.set(key, 1L);
            redisTemplate.expire(key, EXPIRE_TIME_SECOND, TimeUnit.SECONDS);
            return 1L;
        } else {
            return valOps.increment(key, 1L);
        }

    }
}
