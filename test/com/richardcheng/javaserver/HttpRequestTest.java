package com.richardcheng.javaserver;

import org.junit.Assert;
import org.junit.Test;

public class HttpRequestTest {
    @Test
    public void testParseRequest_Root() {
        String request = "GET / HTTP/1.1";
        HttpRequest httpRequest = new HttpRequest();
        String expectedMethod = "GET";
        String expectedUri = "/";
        String expectedVersion = "HTTP/1.1";
        String expectedEndpoint = "root";

        httpRequest.parseRequest(request);

        Assert.assertEquals(expectedMethod, httpRequest.getMethod());
        Assert.assertEquals(expectedUri, httpRequest.getUri());
        Assert.assertEquals(expectedVersion, httpRequest.getVersion());
        Assert.assertEquals(expectedEndpoint, httpRequest.getEndpoint());
    }

    @Test
    public void testParseRequest_Form() {
        String request = "POST /form HTTP/1.1";
        HttpRequest httpRequest = new HttpRequest();
        String expectedMethod = "POST";
        String expectedUri = "/form";
        String expectedVersion = "HTTP/1.1";
        String expectedEndpoint = "form";

        httpRequest.parseRequest(request);

        Assert.assertEquals(expectedMethod, httpRequest.getMethod());
        Assert.assertEquals(expectedUri, httpRequest.getUri());
        Assert.assertEquals(expectedVersion, httpRequest.getVersion());
        Assert.assertEquals(expectedEndpoint, httpRequest.getEndpoint());
    }
}
