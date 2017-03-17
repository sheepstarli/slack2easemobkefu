package com.chenxing.service;

import com.chenxing.data.SlackBot;
import com.chenxing.data.SlackOAuthResult;
import com.chenxing.data.SlackTeamInfoResult;
import com.chenxing.data.SlackUserResult;
import com.chenxing.util.HttpUtil;
import com.chenxing.util.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * OAuthService
 *
 * @author Chenxing Li
 * @date 22/02/2017 12:59
 */
@Slf4j
@Component
public class ApiService {

    @Value("${slack.clientId}")
    private String slackClientId;

    @Value("${slack.clientSecret}")
    private String slackClientSecret;

    public SlackOAuthResult callback(String code) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://slack.com/api/oauth.access")
                .queryParam("client_id", slackClientId)
                .queryParam("client_secret", slackClientSecret)
                .queryParam("code", code);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> exchange = HttpUtil.restTemplate.exchange(builder.build().toString(), HttpMethod.POST, httpEntity, String.class);
        log.info("callback status:{} body:{}", exchange.getStatusCode(), exchange.getBody());
        SlackOAuthResult result = JSONUtil.getObjectMapper().readValue(exchange.getBody(), new TypeReference<SlackOAuthResult>() {
        });
        return result;
    }

    public SlackTeamInfoResult getTeamInfo(SlackBot bot) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://slack.com/api/team.info")
                .queryParam("token", bot.getBotAccessToken());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> exchange = HttpUtil.restTemplate.exchange(builder.build().toString(), HttpMethod.GET, httpEntity, String.class);
        log.debug("getTeamInfo status:{} body:{}", exchange.getStatusCode(), exchange.getBody());
        return JSONUtil.getObjectMapper().readValue(exchange.getBody(), SlackTeamInfoResult.class);
    }

    public SlackUserResult getUserInfo(SlackBot bot, String userId) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://slack.com/api/users.info")
                .queryParam("token", bot.getBotAccessToken())
                .queryParam("user", userId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> exchange = HttpUtil.restTemplate.exchange(builder.build().toString(), HttpMethod.GET, httpEntity, String.class);
        log.debug("getUserInfo status:{} body:{}", exchange.getStatusCode(), exchange.getBody());
        return JSONUtil.getObjectMapper().readValue(exchange.getBody(), SlackUserResult.class);
    }

    public void sendMessage(SlackBot bot, String channelId, String text)  {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://slack.com/api/chat.postMessage")
                .queryParam("token", bot.getBotAccessToken())
                .queryParam("channel", channelId)
                .queryParam("text", text)
                .queryParam("as_user", true);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> exchange = HttpUtil.restTemplate.exchange(builder.build().toString(), HttpMethod.GET, httpEntity, String.class);
        log.debug("sendMessage status:{} body:{}", exchange.getStatusCode(), exchange.getBody());
    }

    public SlackUserResult getImList(SlackBot bot) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://slack.com/api/im.list")
                .queryParam("token", bot.getBotAccessToken());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> exchange = HttpUtil.restTemplate.exchange(builder.build().toString(), HttpMethod.GET, httpEntity, String.class);
        log.debug("getUserInfo status:{} body:{}", exchange.getStatusCode(), exchange.getBody());
        return JSONUtil.getObjectMapper().readValue(exchange.getBody(), SlackUserResult.class);
    }

}
