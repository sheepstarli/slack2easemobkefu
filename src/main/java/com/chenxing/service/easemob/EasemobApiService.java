package com.chenxing.service.easemob;

import com.chenxing.data.easemob.*;
import com.chenxing.util.HttpUtil;
import com.chenxing.util.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EasemobApiService
 *
 * @author Chenxing Li
 * @date 24/02/2017 10:03
 */
@Slf4j
@Component
public class EasemobApiService {

    public EasemobTokenResult getToken(String org, String app, String clientId, String clientSecret) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://a1.easemob.com/{org}/{app}/token");
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("org", org);
        uriParams.put("app", app);
        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> exchange = HttpUtil.restTemplate.exchange(builder.build().toString(), HttpMethod.POST, httpEntity, String.class, uriParams);
        log.info("getToken org:{} app:{} clientId:{} clientSecret:{} status:{} body:{}", org, app, clientId, clientSecret, exchange.getStatusCode(), exchange.getBody());
        return JSONUtil.getObjectMapper().readValue(exchange.getBody(), new TypeReference<EasemobTokenResult>() {
        });
    }

    public EasemobTokenResult sendTextMessage(String org, String app, String token, String from, String to, String message, KefuVisitor kefuVisitor) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://a1.easemob.com/{org}/{app}/messages");
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("org", org);
        uriParams.put("app", app);
        EasemobMessage<EasemobTxtMessageBody> easemobMessage = new EasemobMessage<>();
        easemobMessage.setFrom(from);
        easemobMessage.setTarget(Collections.singletonList(to));
        easemobMessage.setMsg(new EasemobTxtMessageBody(message));
        easemobMessage.setExt(new EasemobExt(new KefuExt(kefuVisitor)));
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<EasemobMessage<EasemobTxtMessageBody>> httpEntity = new HttpEntity<>(easemobMessage, headers);
        ResponseEntity<String> exchange = HttpUtil.restTemplate.exchange(builder.build().toString(), HttpMethod.POST, httpEntity, String.class, uriParams);
        log.info("sendTextMessage org:{} app:{} token:{} from:{} to:{} message:{} status:{} body:{}", org, app, token, from, to, message, exchange.getStatusCode(), exchange.getBody());
        return JSONUtil.getObjectMapper().readValue(exchange.getBody(), new TypeReference<EasemobTokenResult>() {
        });
    }

    public EasemobResult<EasemobUser> addUser(String org, String app, String token, String username, String password, String nickname) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://a1.easemob.com/{org}/{app}/users");
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("org", org);
        uriParams.put("app", app);
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);
        user.put("nickname", nickname);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<List<Map<String, Object>>> httpEntity = new HttpEntity<>(Collections.singletonList(user), headers);
        ResponseEntity<String> exchange = HttpUtil.restTemplate.exchange(builder.build().toString(), HttpMethod.POST, httpEntity, String.class, uriParams);
        log.info("addUser org:{} app:{} token:{} username:{} password:{} nickname:{} status:{} body:{}", org, app, token, username, password, nickname, exchange.getStatusCode(), exchange.getBody());
        return JSONUtil.getObjectMapper().readValue(exchange.getBody(), new TypeReference<EasemobResult<EasemobUser>>() {
        });
    }

    public EasemobResult<EasemobUser> getUser(String org, String app, String token, String username) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://a1.easemob.com/{org}/{app}/users/{username}");
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("org", org);
        uriParams.put("app", app);
        uriParams.put("username", username);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<List<Map<String, Object>>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = HttpUtil.restTemplate.exchange(builder.build().toString(), HttpMethod.GET, httpEntity, String.class, uriParams);
        log.info("getUser org:{} app:{} token:{} username:{} status:{} body:{}", org, app, token, username, exchange.getStatusCode(), exchange.getBody());
        return JSONUtil.getObjectMapper().readValue(exchange.getBody(), new TypeReference<EasemobResult<EasemobUser>>() {
        });
    }

}
