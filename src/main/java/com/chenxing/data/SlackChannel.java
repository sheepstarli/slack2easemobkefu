package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * SlackIm
 *
 * @author Chenxing Li
 * @date 04/03/2017 11:17
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SlackChannel {

    private String id;
    private Boolean isIm = false;
    private String user;
    private Long created;
    private Boolean isUserDeleted;

}
