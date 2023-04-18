package com.solution.interview.controller;

import com.solution.interview.entity.PostBody;
import com.solution.interview.service.WorkflowService;
import com.solution.interview.service.WorkflowServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkflowController {
    private WorkflowService workflowService;

    public WorkflowController(WorkflowServiceImpl workflowServiceImpl) {
        this.workflowService = workflowServiceImpl;
    }

    @PostMapping("/start-workflow")
    public void handleRequest (@RequestBody PostBody postBody) {
        workflowService.handleRequest(postBody);
    }
}
