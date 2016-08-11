package com.richardcheng.presenter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Hashtable;

public class HttpResponseTest {
    @Test
    public void testStatusLine() {
        String code = "200";
        HttpResponse subject = new HttpResponse();
        String expectedResult = "HTTP/1.1 200 OK\r\n";

        String actualResult = subject.statusLine(code);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEntityHeader() {
        int bodyLength = 100;
        HttpResponse subject = new HttpResponse();
        String expectedResult = "Content-Type: text/html\r\nContent-Length: 100\r\n";

        String actualResult = subject.entityHeader(bodyLength);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAllowHeader() {
        Hashtable<String, String> allowedMethods = new Hashtable<>();
        allowedMethods.put("GET", "200");
        allowedMethods.put("OPTIONS", "200");
        HttpResponse subject = new HttpResponse();
        String expectedResult = "Allow: GET,OPTIONS\r\n";

        String actualResult = subject.allowHeader(allowedMethods);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCompleteResponse() {
        String message = "HELLO";
        MockHttpResponse subject = new MockHttpResponse();
        String expectedResult = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\nContent-Length: 5\r\n" +
                "\r\n" +
                "HELLO";

        String actualResult = subject.completeResponse("200", message);

        Assert.assertEquals(expectedResult, actualResult);
    }
}