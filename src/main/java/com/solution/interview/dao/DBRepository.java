package com.solution.interview.dao;

import com.solution.interview.entity.PostBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBRepository extends JpaRepository<PostBody, String> {
}
