package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * SlackOAuthResult
 *
 * @author Chenxing Li
 * @date 22/02/2017 14:01
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SlackOAuthResult {

    private Boolean ok;
    private String accessToken;
    private SlackBot bot;
    private SlackIncomingWebhook incomingWebhook;
    private String scope;
    private String teamId;
    private String teamName;
    private String userId;

}
