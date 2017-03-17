package com.chenxing.controller;

import com.chenxing.data.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * AbstractController
 *
 * @author Chenxing Li
 * @date 22/02/2017 14:19
 */
abstract class AbstractController {
    ResponseEntity<ApiResponse> createApiResponseEntity(Object entity) {
        ApiResponse response = new ApiResponse();
        response.setStatus(ApiResponse.STATUS_OK);
        response.setEntity(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    ResponseEntity<Object> createObjResponseEntity(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
