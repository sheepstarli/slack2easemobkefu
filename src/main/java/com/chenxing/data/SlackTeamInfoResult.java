package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * SlackTeamInfoResult
 *
 * @author Chenxing Li
 * @date 23/02/2017 19:15
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SlackTeamInfoResult {

    private Boolean ok;
    private String error;
    private String warning;
    private SlackTeamInfo team;
}
