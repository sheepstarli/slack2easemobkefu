package com.chenxing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * TestController
 *
 * @author Chenxing Li
 * @date 22/02/2017 08:53
 */
@Slf4j
@RestController
public class TestController extends AbstractController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "v1");
        return map;
    }

}
