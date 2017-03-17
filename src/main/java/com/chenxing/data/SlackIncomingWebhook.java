package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * SlackIncomingWebhook
 *
 * @author Chenxing Li
 * @date 22/02/2017 14:08
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SlackIncomingWebhook {

    private String channel;
    private String channelId;
    private String configurationUrl;
    private String url;

}
