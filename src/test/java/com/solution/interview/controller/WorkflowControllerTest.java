package com.solution.interview.controller;

import com.solution.interview.entity.PostBody;
import com.solution.interview.service.WorkflowServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WorkflowControllerTest {

    @Mock
    private WorkflowServiceImpl workflowService;

    @InjectMocks
    private WorkflowController workflowController;

    @Test
    void handleRequest() {
        PostBody postBody = new PostBody("some-id", "some-name", 30, "some-address");

        workflowController.handleRequest(postBody);

        verify(workflowService, times(1)).handleRequest(postBody);
    }
}