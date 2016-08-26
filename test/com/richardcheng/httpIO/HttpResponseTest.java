package com.richardcheng.httpIO;

import com.richardcheng.httpIO.mock.MockHttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.Hashtable;

public class HttpResponseTest {
    @Test
    public void statusLine_Returns200_IfMatch() {
        String code = "200";
        HttpResponse subject = new HttpResponse();
        String expectedResult = "HTTP/1.1 200 OK\r\n";

        String actualResult = subject.statusLine(code);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void statusLine_Returns404_IfMatch() {
        String code = "404";
        HttpResponse subject = new HttpResponse();
        String expectedResult = "HTTP/1.1 404 Not Found\r\n";

        String actualResult = subject.statusLine(code);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void statusLine_Returns405_IfMatch() {
        String code = "405";
        HttpResponse subject = new HttpResponse();
        String expectedResult = "HTTP/1.1 405 Method Not Allowed\r\n";

        String actualResult = subject.statusLine(code);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void statusLine_Returns418_IfMatch() {
        String code = "418";
        HttpResponse subject = new HttpResponse();
        String expectedResult = "HTTP/1.1 418 I'm a teapot\r\n";

        String actualResult = subject.statusLine(code);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void entityHeader_ReturnsHeader_WithGiven_BodyLength() {
        int bodyLength = 100;
        HttpResponse subject = new HttpResponse();
        String expectedResult = "Content-Type: text/html\r\nContent-Length: 100\r\n";

        String actualResult = subject.entityHeader(bodyLength);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void allowHeader_Returns_HeaderWith_AllowedMethods() {
        Hashtable<String, String> allowedMethods = new Hashtable<>();
        allowedMethods.put("GET", "200");
        allowedMethods.put("OPTIONS", "200");
        HttpResponse subject = new HttpResponse();
        String expectedResult = "Allow: GET,OPTIONS\r\n";

        String actualResult = subject.allowHeader(allowedMethods);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void location_Returns_Root_URL() {
        HttpResponse subject = new HttpResponse();
        int port = 5000;
        String expectedResult = "Location: http://localhost:" + Integer.toString(port) + "/\r\n";

        String actualResult = subject.location(port);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void authHeader_Returns_authMessage() {
        HttpResponse subject = new HttpResponse();
        String expectedResult = "WWW-Authenticate: Basic\r\n";

        String actualResult = subject.authHeader();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void completeResponse_Returns_StatusLine_EntityHeader_Message() {
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