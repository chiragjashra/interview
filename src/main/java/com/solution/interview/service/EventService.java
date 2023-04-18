package com.solution.interview.service;

import com.solution.interview.entity.EventBody;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    public void publishEvent(EventBody eventBody) {
        //  Publish event on any event bus
    }
}
