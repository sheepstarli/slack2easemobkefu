package com.chenxing.data.easemob;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * EasemobResult
 *
 * @author Chenxing Li
 * @date 24/02/2017 16:23
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EasemobResult<T> {
    private String action;
    private String application;
    private String path;
    private String uri;
    private List<T> entities;
    private Long timestamp;
    private Long duration;
    private Long count;
    private String organization;
    private String applicationName;
}
