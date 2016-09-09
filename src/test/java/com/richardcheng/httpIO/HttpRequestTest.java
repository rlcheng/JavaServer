package com.richardcheng.httpIO;

import com.richardcheng.httpIO.mock.MockHttpRequestRange;
import com.richardcheng.javaserver.mock.MockBufferReaderThrowsException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpRequestTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void parseMessage_auth() {
        String expectedAuth = "admin:password";
        String base64Auth = Base64.getEncoder().encodeToString(expectedAuth.getBytes());
        String stream = "GET /logs HTTP/1.1\r\nAuthorization: Basic "+ base64Auth +"\r\n\r\n";
        BufferedReader request = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                stream.getBytes(StandardCharsets.UTF_8))));
        HttpRequest httpRequest = new HttpRequest();

        httpRequest.parseMessage(request);
        String actualAuth = httpRequest.getAuth();

        Assert.assertEquals(expectedAuth, actualAuth);
    }

    @Test
    public void parseMessage_range() {
        String expectedRange = "0-4";
        String stream = "GET /partial_content.txt HTTP/1.1\r\nRange: bytes=0-4\r\n\r\n";
        BufferedReader request = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                stream.getBytes(StandardCharsets.UTF_8))));
        HttpRequest httpRequest = new HttpRequest();

        httpRequest.parseMessage(request);
        String actualRange = httpRequest.getRange();

        Assert.assertEquals(expectedRange, actualRange);
    }

    @Test
    public void parseMessage_etag() {
        String expectedEtag = "5c796969877f11c7bb68138d2379c3dc7ca64a96";
        String stream = "GET /patch-content.txt HTTP/1.1\r\nIf-Match: \"5c796969877f11c7bb68138d2379c3dc7ca64a96\"\r\n\r\n";
        BufferedReader request = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                stream.getBytes(StandardCharsets.UTF_8))));
        HttpRequest httpRequest = new HttpRequest();

        httpRequest.parseMessage(request);
        String actualEtag = httpRequest.getEtag();

        Assert.assertEquals(expectedEtag, actualEtag);
    }

    @Test
    public void parseMessage_endpointWithParameter() {
        String expectedEndpoint = "parameters";
        String expectedParameter = "?variable_1=stuff";
        String stream = "GET /parameters?variable_1=stuff HTTP/1.1\r\n\r\n";
        BufferedReader request = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                stream.getBytes(StandardCharsets.UTF_8))));
        HttpRequest httpRequest = new HttpRequest();

        httpRequest.parseMessage(request);
        String actualParameter = httpRequest.getParameters();
        String actualEndpoint = httpRequest.getEndpoint();

        Assert.assertEquals(expectedParameter, actualParameter);
        Assert.assertEquals(expectedEndpoint, actualEndpoint);
    }

    @Test
    public void parseMessage_throwsException() {
        MockBufferReaderThrowsException request = new MockBufferReaderThrowsException(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                "stuff".getBytes(StandardCharsets.UTF_8))));
        HttpRequest httpRequest = new HttpRequest();

        thrown.expect(RuntimeException.class);
        httpRequest.parseMessage(request);
    }

    @Test
    public void getLog_returns_log() {
        BufferedReader request = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                "GET / HTTP/1.1\r\n\r\n".getBytes(StandardCharsets.UTF_8))));
        HttpRequest httpRequest = new HttpRequest();
        String expectedLog = "GET / HTTP/1.1";

        httpRequest.parseMessage(request);
        String actualLog = httpRequest.getLog();

        Assert.assertEquals(expectedLog, actualLog);
    }

    @Test
    public void resetRange_resets_range_to_emptyString() {
        MockHttpRequestRange subject = new MockHttpRequestRange();
        subject.setRange("100");
        String expectedResult = "";

        subject.resetRange();

        Assert.assertEquals(expectedResult, subject.getRange());
    }
}
