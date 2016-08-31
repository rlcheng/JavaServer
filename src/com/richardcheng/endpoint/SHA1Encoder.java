package com.richardcheng.endpoint;

import java.security.MessageDigest;

public class SHA1Encoder {
    public String encode(String plainText) {
        String encoded = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(plainText.getBytes());
            byte[] bytes = messageDigest.digest();
            encoded = bytesToHexString(bytes);
        } catch (Exception e) {

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
