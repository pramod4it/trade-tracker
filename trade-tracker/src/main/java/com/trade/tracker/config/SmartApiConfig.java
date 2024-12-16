package com.trade.tracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.trade.tracker.smartapi.SmartConnect;
import com.trade.tracker.smartapi.models.User;
import com.trade.tracker.service.TOTPService;

@Configuration
public class SmartApiConfig {

    @Value("${smartapi.api.key}")
    private String apiKey;

    @Value("${smartapi.client.code}")
    private String clientCode;

    @Value("${smartapi.password}")
    private String password;

    private final TOTPService totpService;

    public SmartApiConfig(TOTPService totpService) {
        this.totpService = totpService;
    }

    @Bean
    SmartConnect smartConnect() {
        try {
            SmartConnect smartConnect = new SmartConnect(apiKey);
            
            String totp = totpService.generateTOTP();

            User tokenSet = smartConnect.generateSession(clientCode, password, totp);
            smartConnect.setAccessToken(tokenSet.accessToken);
            smartConnect.setRefreshToken(tokenSet.refreshToken);
            return smartConnect;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize SmartConnect", e);
        }
    }
}

