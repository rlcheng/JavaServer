package com.richardcheng.httpIO;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpRequestTest {
    @Test
    public void parseMessage_ParseRequest_And_ForwardSlash_AsRootEndpoint() {
        BufferedReader request = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                "GET / HTTP/1.1\r\n\r\n".getBytes(StandardCharsets.UTF_8))));
        HttpRequest httpRequest = new HttpRequest();
        String expectedMethod = "GET";
        String expectedUri = "/";
        String expectedVersion = "HTTP/1.1";
        String expectedEndpoint = "root";

        httpRequest.parseMessage(request);

        Assert.assertEquals(expectedMethod, httpRequest.getMethod());
        Assert.assertEquals(expectedUri, httpRequest.getUri());
        Assert.assertEquals(expectedVersion, httpRequest.getVersion());
        Assert.assertEquals(expectedEndpoint, httpRequest.getEndpoint());
    }

    @Test
    public void parseMessage_ParseRequest_And_ForwardSlashForm_AsFormEndpoint() {
        BufferedReader request = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                "POST /form HTTP/1.1\r\n\r\n".getBytes(StandardCharsets.UTF_8))));
        HttpRequest httpRequest = new HttpRequest();
        String expectedMethod = "POST";
        String expectedUri = "/form";
        String expectedVersion = "HTTP/1.1";
        String expectedEndpoint = "form";

        httpRequest.parseMessage(request);

        Assert.assertEquals(expectedMethod, httpRequest.getMethod());
        Assert.assertEquals(expectedUri, httpRequest.getUri());
        Assert.assertEquals(expectedVersion, httpRequest.getVersion());
        Assert.assertEquals(expectedEndpoint, httpRequest.getEndpoint());
    }

    @Test
    public void parseMessage_set_and_getData() {
        BufferedReader request = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                "POST /form HTTP/1.1\r\nContent-Length: 13\r\n\r\ndata=garfield".getBytes(StandardCharsets.UTF_8))));
        String expectedData = "data=garfield";
        HttpRequest httpRequest = new HttpRequest();

        httpRequest.parseMessage(request);
        String actualData = httpRequest.getData();

        Assert.assertEquals(expectedData, actualData);
    }
}
