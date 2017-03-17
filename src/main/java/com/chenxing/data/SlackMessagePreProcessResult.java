package com.chenxing.data;

import lombok.Data;

/**
 * SlackMessagePreProcessResult
 *
 * @author Chenxing Li
 * @date 04/03/2017 12:20
 */
@Data
public class SlackMessagePreProcessResult {

    private SlackMessage slackMessage;
    private SlackMessageType slackMessageType;
    private SlackUser slackUser;
    private SlackTeamInfo slackTeamInfo;
    private Boolean isAt;

    public SlackMessagePreProcessResult() {
        super();
    }

    public SlackMessagePreProcessResult(SlackTeamInfo slackTeamInfo, SlackUser slackUser, SlackMessageType slackMessageType, SlackMessage slackMessage, Boolean isAt) {
        super();
        this.slackTeamInfo = slackTeamInfo;
        this.slackUser = slackUser;
        this.slackMessageType = slackMessageType;
        this.slackMessage = slackMessage;
        this.isAt = isAt;
    }

}
