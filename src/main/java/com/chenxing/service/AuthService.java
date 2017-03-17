package com.chenxing.service;

import com.chenxing.data.SlackOAuthResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * AuthService
 *
 * @author Chenxing Li
 * @date 23/02/2017 18:33
 */
@Component
public class AuthService {

    @Autowired
    private BotService botService;

    public SlackOAuthResult auth(String code) throws IOException {
        return botService.auth(code);
    }
}
