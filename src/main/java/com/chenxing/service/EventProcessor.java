package com.chenxing.service;

import java.util.Map;

/**
 * EventProcessor
 *
 * @author Chenxing Li
 * @date 22/02/2017 12:15
 */
public interface EventProcessor {

    String getSupportType();

    Object process(Map<String, Object> body);
}
