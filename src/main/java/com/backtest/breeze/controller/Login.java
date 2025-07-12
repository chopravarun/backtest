package com.backtest.breeze.controller;

import com.backtest.breeze.config.BConfig;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/")
public class Login {

    @Autowired
    private BConfig cfg;


    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        String loginUrl = cfg.getLoginUrl()+ URLEncoder.encode(cfg.getApiKey(), StandardCharsets.UTF_8);
        response.sendRedirect(loginUrl);
    }

    @PostMapping("/token")
    public String acceptToken(@RequestParam("apisession") String token) {
        try {
            cfg.getBreezeConnect().generateSession(cfg.getApiSecret(), token);

            return "success";
        } catch (Exception e) {
            return "Login Failure";
        }
    }
}
