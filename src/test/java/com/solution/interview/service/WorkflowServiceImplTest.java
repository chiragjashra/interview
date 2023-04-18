package com.solution.interview.service;

import static org.mockito.Mockito.*;

import com.solution.interview.entity.EventBody;
import com.solution.interview.entity.RedisBody;
import com.solution.interview.exception.SomeAPIException;
import com.solution.interview.exception.SomeEventException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.solution.interview.entity.PostBody;

@ExtendWith(MockitoExtension.class)
public class WorkflowServiceImplTest {

    private WorkflowServiceImpl workflowService;

    @Mock
    private DBService dbService;

    @Mock
    private RedisService redisService;

    @Mock
    private EventService eventService;

    @Mock
    private ApiClient apiClient;

    @BeforeEach
    public void setUp() {
        workflowService = new WorkflowServiceImpl(dbService, redisService, eventService, apiClient);
    }

    @Test
    public void testHandleRequest_success() throws Exception {
        // Arrange
        PostBody postBody = new PostBody("some-id", "some-name", 30, "some-address");

        // Act
        workflowService.handleRequest(postBody);

        // Assert
        verify(dbService).updateDB(postBody);
        verify(apiClient).callAPI();
        verify(redisService).updateRedis(any(RedisBody.class));
        verify(eventService).publishEvent(any(EventBody.class));
        verifyNoMoreInteractions(dbService, redisService, eventService, apiClient);
    }

    @Test
    public void testHandleRequest_apiException() throws Exception {
        // Arrange
        PostBody postBody = new PostBody("some-id", "some-name", 30, "some-address");
        doThrow(new SomeAPIException()).when(apiClient).callAPI();

        // Act
        workflowService.handleRequest(postBody);

        // Assert
        verify(dbService).updateDB(postBody);
        verify(apiClient).callAPI();
        verify(apiClient).rollbackAPICall();
        verifyNoMoreInteractions(dbService, redisService, eventService, apiClient);
    }

    @Test
    public void testHandleRequest_eventException() throws Exception {
        // Arrange
        PostBody postBody = new PostBody("some-id", "some-name", 30, "some-address");
        doThrow(new SomeEventException()).when(eventService).publishEvent(any(EventBody.class));

        // Act
        workflowService.handleRequest(postBody);

        // Assert
        verify(dbService).updateDB(postBody);
        verify(apiClient).callAPI();
        verify(redisService).updateRedis(any(RedisBody.class));
        verify(eventService).publishEvent(any(EventBody.class));
        verify(apiClient).rollbackAPICall();
        verifyNoMoreInteractions(dbService, redisService, eventService, apiClient);
    }
}