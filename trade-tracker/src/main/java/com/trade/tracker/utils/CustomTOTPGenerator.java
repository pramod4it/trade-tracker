package com.trade.tracker.utils;

import java.nio.ByteBuffer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

public class CustomTOTPGenerator {

    public static String generateTOTP(String secretKey, long timeStep, int digits) {
        try {
            // Decode Base32 secret key
            byte[] keyBytes = new Base32().decode(secretKey);

            // Generate time step (30-second intervals)
            long timeWindow = System.currentTimeMillis() / 1000 / timeStep;
            byte[] data = ByteBuffer.allocate(8).putLong(timeWindow).array();

            // HMAC-SHA1 computation
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(new SecretKeySpec(keyBytes, "HmacSHA1"));
            byte[] hmac = mac.doFinal(data);

            // Extract dynamic binary code
            int offset = hmac[hmac.length - 1] & 0xF;
            int binary = ((hmac[offset] & 0x7F) << 24) | ((hmac[offset + 1] & 0xFF) << 16)
                    | ((hmac[offset + 2] & 0xFF) << 8) | (hmac[offset + 3] & 0xFF);

            // Generate TOTP code
            int otp = binary % (int) Math.pow(10, digits);
            return String.format("%0" + digits + "d", otp);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate TOTP", e);
        }
    }
    
    public static void main(String[] args) {
		System.out.println(generateTOTP("SRXTKQCCJLXT4E5LCSEQQVJGHI", 30, 6));
	}
}
