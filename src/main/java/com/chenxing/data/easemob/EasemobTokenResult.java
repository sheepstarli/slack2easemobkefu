package com.chenxing.data.easemob;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * EasemobTokenResult
 *
 * @author Chenxing Li
 * @date 24/02/2017 10:14
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EasemobTokenResult {
    private String accessToken;
    private Long expiresIn;
    private String application;
}
