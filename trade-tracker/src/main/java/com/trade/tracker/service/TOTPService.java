package com.trade.tracker.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.trade.tracker.utils.CustomTOTPGenerator;

@Service
public class TOTPService {
	
    @Value("${smartapi.secret.key}")
    private String secretKey;
    
    public String generateTOTP() {
    	return CustomTOTPGenerator.generateTOTP(secretKey, 30, 6).trim();
    }
}
