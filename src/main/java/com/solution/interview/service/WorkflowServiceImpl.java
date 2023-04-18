package com.solution.interview.service;

import com.solution.interview.entity.EventBody;
import com.solution.interview.entity.PostBody;
import com.solution.interview.entity.RedisBody;
import com.solution.interview.exception.SomeAPIException;
import com.solution.interview.exception.SomeEventException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorkflowServiceImpl implements WorkflowService {
    private DBService dbService;
    private RedisService redisService;
    private EventService eventService;
    private ApiClient apiClient;
    private RestTemplate restTemplate = new RestTemplate();

    public WorkflowServiceImpl(DBService dbService, RedisService redisService, EventService eventService, ApiClient apiClient) {
        this.dbService = dbService;
        this.redisService = redisService;
        this.eventService = eventService;
        this.apiClient = apiClient;
    }

    @Override
    @Transactional
    public void handleRequest(PostBody postBody) {
        String apiResponseData;

        try {
            // Update the database
            dbService.updateDB(postBody);

            // Call the API endpoint
            apiClient.callAPI();

            // Update Redis
            RedisBody redisBody = new RedisBody("REDIS_ID_1", "some-state");
            redisService.updateRedis(redisBody);

            // Trigger an event on any event bus
            EventBody eventBody = new EventBody("some-key", "some-value");
            eventService.publishEvent(eventBody);
        } catch (SomeAPIException | SomeEventException e) {
            apiClient.rollbackAPICall();
        }
    }
}
