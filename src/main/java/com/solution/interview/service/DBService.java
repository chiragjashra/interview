package com.solution.interview.service;

import com.solution.interview.dao.DBRepository;
import com.solution.interview.entity.PostBody;
import org.springframework.stereotype.Service;

@Service
public class DBService {
    private DBRepository dbRepository;

    public DBService(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public void updateDB(PostBody postBody) {
        dbRepository.save(postBody);
    }
}
