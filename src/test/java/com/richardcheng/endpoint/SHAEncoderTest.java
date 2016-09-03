package com.richardcheng.endpoint;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SHAEncoderTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void encode_ReturnsSHA1_encodedString() {
        String plainText = "richard";
        String expectedSHA1 = "320bca71fc381a4a025636043ca86e734e31cf8b";
        SHAEncoder subject = new SHAEncoder();

        String actualSHA1 = subject.encode(plainText, "SHA1");

        Assert.assertEquals(expectedSHA1, actualSHA1);
    }

    @Test
    public void encode_MessageDigest_getInstance_throwsException() {
        String plainText = "richard";
        SHAEncoder subject = new SHAEncoder();

        thrown.expect(RuntimeException.class);
        subject.encode(plainText, "bad_algorithm");
    }
}