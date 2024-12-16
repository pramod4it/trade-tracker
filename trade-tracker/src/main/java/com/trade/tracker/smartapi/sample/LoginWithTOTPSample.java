package com.trade.tracker.smartapi.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trade.tracker.smartapi.SmartConnect;
import com.trade.tracker.smartapi.models.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginWithTOTPSample {
	private static final  Logger log = LoggerFactory.getLogger(LoginWithTOTPSample.class);
	public static void main(String[] args) {
		String clientID = System.getProperty("clientID");
		String clientPass = System.getProperty("clientPass");
		String apiKey = System.getProperty("apiKey");
		String totp = System.getProperty("totp");
		SmartConnect smartConnect = new SmartConnect(apiKey);
		User user = smartConnect.generateSession(clientID, clientPass, totp);
		String feedToken = user.getFeedToken();
		log.info("feedToken : {}",feedToken);
	}
}
