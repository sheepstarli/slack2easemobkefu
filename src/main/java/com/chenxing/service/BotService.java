package com.chenxing.service;

import com.chenxing.data.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * BotService
 *
 * @author Chenxing Li
 * @date 22/02/2017 14:27
 */
@Slf4j
@Component
public class BotService {

    @Autowired
    private ApiService apiService;

    private final Map<String, SlackBot> slackBotMap = new HashMap<>();
    private final Map<String, SlackTeamInfo> slackTeamMap = new HashMap<>();
    private final Map<String, SlackUser> slackUserMap = new HashMap<>();
    private final Map<String, String> slackUserLastChannelMap = new HashMap<>();

    public SlackOAuthResult auth(String code) throws IOException {
        SlackOAuthResult result = apiService.callback(code);
        slackBotMap.put(result.getTeamId(), result.getBot());
        return result;
    }
    public void cacheSlackUserLashChannel(String teamId, String userId, String channelId) {
        slackUserLastChannelMap.put(teamId + "_" + userId, channelId);
    }

    private String getSlackUserLastChannel(String teamId, String userId) {
        return slackUserLastChannelMap.get(teamId + "_" + userId);
    }

    public void sendMessageToUser(String teamId, String to, String channelId, String message) {
//        String channelId = getSlackUserLastChannel(teamId, to);
        if (StringUtils.isBlank(channelId)) {
            log.warn("do not have channel teamId:{} userId:{}", teamId, to);
            return;
        }
        SlackBot slackBot = slackBotMap.get(teamId);
        if (slackBot == null) {
            log.warn("team {} do not have a bot.", teamId);
            return;
        }
        apiService.sendMessage(slackBot, channelId, generateAt(to) + message);
    }

    public SlackMessagePreProcessResult preProcessSlackMessage(String teamId, SlackMessage slackMessage) throws IOException {
        SlackUser slackUser = getSlackUser(teamId, slackMessage.getUser());
        SlackTeamInfo slackTeamInfo = getSlackTeamInfo(teamId);
        SlackMessageType slackMessageType = identifyMessageType(slackMessage.getChannel());
        return new SlackMessagePreProcessResult(slackTeamInfo, slackUser, slackMessageType, slackMessage, checkAt(slackBotMap.get(teamId), slackMessage.getText()));
    }

    private SlackMessageType identifyMessageType(String channelId) {
        if (StringUtils.startsWith(channelId, "C")) {
            return SlackMessageType.PublicChannel;
        } else if (StringUtils.startsWith(channelId, "D")) {
            return SlackMessageType.DirectMessage;
        } else if (StringUtils.startsWith(channelId, "G")) {
            return SlackMessageType.PrivateChannel;
        } else {
            return SlackMessageType.Unknown;
        }
    }

//    public void replyMessage(String teamId, String channelId, String to, String message) {
//        SlackBot slackBot = slackBotMap.get(teamId);
//        if (slackBot == null) {
//            log.warn("team {} do not have a bot.", teamId);
//            return;
//        }
//        if (!checkAt(slackBot, message)) {
//            log.warn("message {} not at bot {}", message, slackBot.getBotUserId());
//            return;
//        }
//        String extraInfo = null;
//        try {
//            extraInfo = getSlackTeamInfo(teamId).getName() + "-" + getSlackUser(teamId, to).getRealName();
//        } catch (Exception e) {
//            log.error("get teamInfo or userInfo error", e);
//        }
//        apiService.sendMessage(slackBot, channelId, generateAt(to) + " " + extraInfo + " 这是回复内容啦啦啦~" + System.currentTimeMillis());
//    }

    private boolean checkAt(SlackBot slackBot, String message) {
        return StringUtils.contains(message, "<@" + slackBot.getBotUserId() + ">");
    }

    private String generateAt(String userId) {
        return "<@" + userId + ">";
    }

    public SlackTeamInfo getSlackTeamInfo(String teamId) throws IOException {
        SlackBot slackBot = slackBotMap.get(teamId);
        if (slackBot == null) {
            log.warn("team {} do not have a bot.", teamId);
            return null;
        }
        SlackTeamInfo slackTeamInfo = slackTeamMap.get(teamId);
        if (slackTeamInfo == null) {
            SlackTeamInfoResult teamInfo = apiService.getTeamInfo(slackBot);
            slackTeamInfo = teamInfo.getTeam();
            slackTeamMap.put(teamId, slackTeamInfo);
        }
        return slackTeamInfo;
    }

    private SlackUser getSlackUser(String teamId, String user) throws IOException {
        SlackBot slackBot = slackBotMap.get(teamId);
        if (slackBot == null) {
            log.warn("team {} do not have a bot.", teamId);
            return null;
        }
        SlackUser slackUser = slackUserMap.get(teamId + "_" + user);
        if (slackUser == null) {
            SlackUserResult userInfo = apiService.getUserInfo(slackBot, user);
            slackUser = userInfo.getUser();
            slackUserMap.put(teamId + "_" + user, slackUser);
        }
        return slackUser;
    }
}
