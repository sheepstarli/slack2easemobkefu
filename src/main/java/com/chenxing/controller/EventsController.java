package com.chenxing.controller;

import com.chenxing.service.EventService;
import com.chenxing.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * EventsController
 *
 * @author Chenxing Li
 * @date 22/02/2017 11:59
 */
@Slf4j
@Controller
public class EventsController extends AbstractController {

    @Autowired
    private EventService eventService;

    @RequestMapping(name = "/events/callback", method = RequestMethod.POST)
    public ResponseEntity<Object> events(@RequestBody Map<String, Object> body) {
        log.info("/events/callback body:{}", JSONUtil.mapToJsonString(body));
        return createObjResponseEntity(eventService.process(body));
    }
}
