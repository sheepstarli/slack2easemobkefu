package com.chenxing.controller;

import com.chenxing.data.ApiResponse;
import com.chenxing.data.SlackOAuthResult;
import com.chenxing.service.ApiService;
import com.chenxing.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * OAuthController
 *
 * @author Chenxing Li
 * @date 22/02/2017 10:22
 */
@Slf4j
@Controller
public class OAuthController extends AbstractController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/oauth", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> oauth() {
        return createApiResponseEntity(null);
    }

    @RequestMapping(value = "/oauth/callback", method = RequestMethod.GET)
    public ModelAndView oauthCallback(@RequestParam(value = "code", required = false) String code, ModelAndView mav) throws IOException {
        log.info("/oauth/callback code:{}", code);
        authService.auth(code);
        mav.setViewName("/welcome");
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        log.info("/index");
        mav.setViewName("/index");
        return mav;
    }

}
