package com.richardcheng.endpoint;

import org.junit.Assert;
import org.junit.Test;

public class SHA1EncoderTest {
    @Test
    public void encode_ReturnsSHA1_encodedString() {
        String plainText = "richard";
        String expectedSHA1 = "320bca71fc381a4a025636043ca86e734e31cf8b";

        String actualSHA1 = new SHA1Encoder().encode(plainText);

        Assert.assertEquals(expectedSHA1, actualSHA1);
    }
}