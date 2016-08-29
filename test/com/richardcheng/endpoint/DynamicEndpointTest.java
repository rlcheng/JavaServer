package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpRequestGet;
import com.richardcheng.endpoint.mock.MockHttpResponsePartial;
import com.richardcheng.httpIO.mock.MockHttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

public class DynamicEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
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
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingtoMatch";
        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>();
        directoryList.put("file1", 1);
        DynamicEndpoint subject = new DynamicEndpoint(new MockHttpResponse(), directoryList, null);

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns206_WithPartialContent_ifMatch() {
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String endpoint = "partial_content.txt";
        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>();
        directoryList.put("file1", 1);
        directoryList.put("image.png", 1);
        directoryList.put("partial_content.txt", 1);
        String path = "/Users/richardcheng/Documents/cob_spec/public/";
        DynamicEndpoint subject = new DynamicEndpoint(new MockHttpResponsePartial(), directoryList, path);
        String expectedRouteResponse = "HTTP/1.1 206 Partial Content\r\nContent-Type: text/html\r\nContent-Length: 5\r\n\r\nThis ";

        subject.match(endpoint);
        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}