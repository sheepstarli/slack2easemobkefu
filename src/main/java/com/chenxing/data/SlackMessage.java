package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * SlackMessage
 *
 * @author Chenxing Li
 * @date 22/02/2017 14:43
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SlackMessage {
    private String type;
    private String subtype;
    private String channel;
    private String user;
    private String text;
    private String ts;
    private String botId;
}
