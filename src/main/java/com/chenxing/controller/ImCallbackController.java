package com.chenxing.controller;

import com.chenxing.data.easemob.IMCallback;
import com.chenxing.service.easemob.EasemobService;
import com.chenxing.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ImCallbackController
 *
 * @author Chenxing Li
 * @date 01/03/2017 18:36
 */
@Slf4j
@RestController
public class ImCallbackController {

    @Autowired
    private EasemobService easemobService;

    @RequestMapping(value = "/imcallback", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void imcallback(@RequestBody IMCallback imCallback) {
        log.info("/imcallback body:{}", JSONUtil.mapToJsonString(imCallback));
        easemobService.callback(imCallback);
    }
}
