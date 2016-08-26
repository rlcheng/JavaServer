package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpRequestGet;
import com.richardcheng.endpoint.mock.MockHttpRequestNoMatch;
import com.richardcheng.endpoint.mock.MockHttpResponseMethodOptions2;
import org.junit.Assert;
import org.junit.Test;

public class MethodOptions2EndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "method_options2";
        MethodOptions2Endpoint subject = new MethodOptions2Endpoint(new MockHttpResponseMethodOptions2());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingtoMatch";
        MethodOptions2Endpoint subject = new MethodOptions2Endpoint(new MockHttpResponseMethodOptions2());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns200Response_IfMethodMatch() {
        MethodOptions2Endpoint subject = new MethodOptions2Endpoint(new MockHttpResponseMethodOptions2());
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nAllow: GET,OPTIONS\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns405Response_IfNo_MethodMatch() {
        MethodOptions2Endpoint subject = new MethodOptions2Endpoint(new MockHttpResponseMethodOptions2());
        MockHttpRequestNoMatch httpRequest = new MockHttpRequestNoMatch();
        String expectedRouteResponse = "HTTP/1.1 405 Method Not Allowed\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}