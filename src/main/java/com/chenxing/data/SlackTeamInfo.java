package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * SlackTeamInfo
 *
 * @author Chenxing Li
 * @date 23/02/2017 18:14
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SlackTeamInfo {

    private String id;
    private String name;
    private String domain;
    private String emailDomain;
    private SlackTeamIcon icon;

}
