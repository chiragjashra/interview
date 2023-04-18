package com.solution.interview.service;

import com.solution.interview.dao.RedisRepository;
import com.solution.interview.entity.RedisBody;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private RedisRepository redisRepository;

    public RedisService(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public void updateRedis(RedisBody redisBody) {
        redisRepository.save(redisBody);
    }
}
