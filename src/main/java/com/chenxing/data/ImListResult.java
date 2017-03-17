package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * ImListResult
 *
 * @author Chenxing Li
 * @date 04/03/2017 11:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ImListResult {

    private Boolean ok;
    private String error;
    private String warning;
    private SlackUser user;

}
