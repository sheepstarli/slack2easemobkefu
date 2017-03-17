package com.chenxing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EventService
 *
 * @author Chenxing Li
 * @date 22/02/2017 12:21
 */
@Slf4j
@Component
public class EventService {

    private final Map<String, EventProcessor> processorMap;

    private final String slackVerifyToken;

    @Autowired
    public EventService(List<EventProcessor> processors,
                        @Value("${slack.verifyToken}") String slackVeryfyToken) {
        this.processorMap = new HashMap<>();
        processors.forEach(processor -> processorMap.put(processor.getSupportType(), processor));
        this.slackVerifyToken = slackVeryfyToken;
    }

    public Object process(Map<String, Object> body) {
        String token = (String) body.get("token");
        if (!slackVerifyToken.equals(token)) {
            log.warn("token not correct, body is {}", body);
            return null;
        }
        String callBackType = (String) body.get("type");
        log.info("callback type is :{}", callBackType);
        if ("event_callback".equals(callBackType)) {
            Map<String, Object> event = (Map<String, Object>) body.get("event");
            String type = (String) event.get("type");
            EventProcessor eventProcessor = processorMap.get(type);
            if (eventProcessor == null) {
                log.warn("{} not support yet, body is {}", type, body);
                return null;
            }
            return eventProcessor.process(body);
        } else if ("url_verification".equals(callBackType)) {
            return body.get("challenge");
        } else {
            log.error("bad callback type:{}", callBackType);
            return null;
        }
    }
}
