package com.chenxing.service.easemob;

import com.chenxing.data.easemob.*;
import com.chenxing.service.BotService;
import com.chenxing.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EasemobService
 *
 * @author Chenxing Li
 * @date 24/02/2017 16:39
 */
@Slf4j
@Component
public class EasemobService {

    private final EasemobApiService easemobApiService;
    private final BotService botService;

    @Autowired
    public EasemobService(EasemobApiService easemobApiService, BotService botService) {
        this.easemobApiService = easemobApiService;
        this.botService = botService;
    }


    @Value("${easemob.clientId}")
    private String easemobClientId;
    @Value("${easemob.clientSecret}")
    private String easemobClientSecret;
    @Value("${easemob.org}")
    private String orgName;
    @Value("${easemob.app}")
    private String app;
    @Value("${easemob.imServiceNumber}")
    private String imServiceNumber;

    private static final Map<String, String> tokenMap = new HashMap<>();
    private static final Map<String, EasemobUser> userMap = new HashMap<>();

    public void sendToEasemob(String easemobUserId, KefuVisitor kefuVisitor, String message) throws IOException {
        String token = getToken();
        EasemobUser user = getUser(token, easemobUserId, kefuVisitor.getUserNickname());
        easemobApiService.sendTextMessage(orgName, app, token, user.getUsername(), imServiceNumber, message, kefuVisitor);
    }

    private String getToken() throws IOException {
        String appKey = orgName + "#" + app;
        String token = tokenMap.get(appKey);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        EasemobTokenResult tokenResult = easemobApiService.getToken(orgName, app, easemobClientId, easemobClientSecret);
        if (tokenResult != null && StringUtils.isNotBlank(tokenResult.getAccessToken())) {
            tokenMap.put(appKey, tokenResult.getAccessToken());
            return tokenResult.getAccessToken();
        }
        return null;
    }

    private EasemobUser getUser(String token, String username, String nickname) throws IOException {
        EasemobUser user = userMap.get(username);
        if (user != null) {
            return user;
        }
        EasemobResult<EasemobUser> userResult = easemobApiService.getUser(orgName, app, token, username);
        if (userResult != null && userResult.getEntities() != null && !userResult.getEntities().isEmpty()) {
            userMap.put(username, userResult.getEntities().get(0));
            return userResult.getEntities().get(0);
        }
        userResult = easemobApiService.addUser(orgName, app, token, username, username + RandomStringUtils.random(3), nickname);
        userMap.put(username, userResult.getEntities().get(0));
        return userResult.getEntities().get(0);
    }

    public void callback(IMCallback imCallback) {
        if (!imServiceNumber.equals(imCallback.getFrom())) {
            log.warn("message not from imServiceNumber message:{}", JSONUtil.mapToJsonString(imCallback));
            return;
        }
        if (!"chat".equals(imCallback.getEventType())) {
            log.warn("message type is not chat message:{}", JSONUtil.mapToJsonString(imCallback));
            return;
        }
        List<EasemobMessageBody> bodies = imCallback.getPayload().getBodies();
        if (bodies == null || bodies.isEmpty()) {
            log.warn("message do not have bodies message:{}", JSONUtil.mapToJsonString(imCallback));
            return;
        }
        EasemobMessageBody easemobMessageBody = bodies.get(0);
        String message = null;
        switch (easemobMessageBody.getType()) {
            case "txt":
                message = ((EasemobTxtMessageBody) easemobMessageBody).getMsg();
                break;
            case "img":
                message = "图片消息";
                break;
            case "video":
                message = "视频消息";
                break;
            case "file":
                message = "文件消息";
                break;
            case "loc":
                message = "地理位置消息";
                break;
            case "cmd":
                message = "命令消息";
                break;
            default:
                message = "未知消息";
        }
        String to = imCallback.getTo();
        String[] split = StringUtils.split(to, "_");
        String teamId = split[0];
        String userId = split[1];
        String channelId = split[2];
        botService.sendMessageToUser(teamId, userId, channelId, message);
    }
}
