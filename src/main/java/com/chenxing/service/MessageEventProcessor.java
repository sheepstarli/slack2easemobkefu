package com.chenxing.service;

import com.chenxing.data.SlackMessage;
import com.chenxing.data.SlackMessagePreProcessResult;
import com.chenxing.data.SlackMessageType;
import com.chenxing.data.SlackUser;
import com.chenxing.data.easemob.KefuVisitor;
import com.chenxing.service.easemob.EasemobService;
import com.chenxing.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * MessageEventProcessor
 *
 * @author Chenxing Li
 * @date 22/02/2017 12:25
 */
@Slf4j
@Component
public class MessageEventProcessor implements EventProcessor {

    @Autowired
    private BotService botService;

    @Autowired
    private EasemobService easemobService;

    @Override
    public String getSupportType() {
        return "message";
    }

    @Override
    public Object process(Map<String, Object> body) {
        SlackMessage slackMessage = JSONUtil.getObjectMapper().convertValue(body.get("event"), SlackMessage.class);
        log.info("message channel:{} user:{} ts:{} text:{}", slackMessage.getChannel(), slackMessage.getUser(), slackMessage.getTs(), slackMessage.getText());
        if (!"bot_message".equals(slackMessage.getSubtype()) && StringUtils.isBlank(slackMessage.getBotId())) {
            String teamId = (String) body.get("team_id");
//            botService.cacheSlackUserLashChannel(teamId, slackMessage.getUser(), slackMessage.getChannel());
//            botService.replyMessage(teamId, slackMessage.getChannel(), slackMessage.getUser(), slackMessage.getText());
            try {
                SlackMessagePreProcessResult preProcessResult = botService.preProcessSlackMessage(teamId, slackMessage);
                String easemobUserId = StringUtils.join(new Object[] {teamId, slackMessage.getUser(), slackMessage.getChannel()}, "_");
                switch (preProcessResult.getSlackMessageType()) {
                    case PrivateChannel:
                    case PublicChannel:
                        if (!preProcessResult.getIsAt()) {
                            log.warn("group message not at");
                            return null;
                        }
                    case DirectMessage:
                        easemobService.sendToEasemob(easemobUserId, new KefuVisitor(preProcessResult.getSlackUser().getRealName(), preProcessResult.getSlackTeamInfo().getName()), slackMessage.getText());
                        break;
                    case Unknown:
                        log.error("slack message type not support");
                }

            } catch (Exception e) {
                log.error("process exception", e);
            }
        }
        return null;
    }

}
