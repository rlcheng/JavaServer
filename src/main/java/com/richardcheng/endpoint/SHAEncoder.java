package com.richardcheng.endpoint;

import java.security.MessageDigest;

public class SHAEncoder {
    public String encode(String plainText, String algorithmType) {
        String encoded = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithmType);
            messageDigest.update(plainText.getBytes());
            byte[] bytes = messageDigest.digest();
            encoded = bytesToHexString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encoded;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
