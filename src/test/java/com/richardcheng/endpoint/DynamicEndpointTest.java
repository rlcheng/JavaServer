package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpRequestPut;
import com.richardcheng.endpoint.mock.MockHttpResponsePartial;
import com.richardcheng.httpIO.mock.MockHttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

public class DynamicEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch_isText_withTxtExtension() {
        String endpoint = "partial_content.txt";
        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>(3);
        directoryList.put("file1", 1);
        directoryList.put("image.png", 1);
        directoryList.put("partial_content.txt", 1);
        DynamicEndpoint subject = new DynamicEndpoint(new MockHttpResponsePartial(), directoryList, null);

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsTrue_if_StringMatch_isText_noFileExtension() {
        String endpoint = "file1";
        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>(3);
        directoryList.put("file1", 1);
        directoryList.put("image.png", 1);
        directoryList.put("partial_content.txt", 1);
        DynamicEndpoint subject = new DynamicEndpoint(new MockHttpResponsePartial(), directoryList, null);

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsTrue_if_StringMatch_isImage() {
        String endpoint = "image.png";
        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>(3);
        directoryList.put("file1", 1);
        directoryList.put("image.png", 1);
        directoryList.put("partial_content.txt", 1);
        DynamicEndpoint subject = new DynamicEndpoint(new MockHttpResponsePartial(), directoryList, null);

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }



    @Test
    public void match_ReturnsFalse_if_StringNotMatch() {
        String endpoint = "notGoingtoMatch";
        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>();
        directoryList.put("file1", 1);
        DynamicEndpoint subject = new DynamicEndpoint(new MockHttpResponse(), directoryList, null);

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns405_requestNotAllowed() {
        MockHttpRequestPut httpRequest = new MockHttpRequestPut();
        String endpoint = "image.png";
        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>();
        directoryList.put("file1", 1);
        directoryList.put("image.png", 1);
        directoryList.put("partial_content.txt", 1);
        String path = "path";
        DynamicEndpoint subject = new DynamicEndpoint(new MockHttpResponsePartial(), directoryList, path);
        String expectedRouteResponse = "HTTP/1.1 405 Method Not Allowed\r\n";

        subject.match(endpoint);
        byte[] byteArray = subject.route(httpRequest);

        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}