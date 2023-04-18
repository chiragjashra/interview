package com.solution.interview.dao;

import com.solution.interview.entity.RedisBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends JpaRepository<RedisBody, String> {
}
