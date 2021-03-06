package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpRequestGet;
import com.richardcheng.endpoint.mock.MockHttpRequestNoMatch;
import com.richardcheng.endpoint.mock.MockHttpResponseMethodOptions;
import org.junit.Assert;
import org.junit.Test;

public class MethodOptionsEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "method_options";
        MethodOptionsEndpoint subject = new MethodOptionsEndpoint(new MockHttpResponseMethodOptions());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringNotMatch() {
        String endpoint = "notGoingtoMatch";
        MethodOptionsEndpoint subject = new MethodOptionsEndpoint(new MockHttpResponseMethodOptions());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns200Response_IfMethodMatch() {
        MethodOptionsEndpoint subject = new MethodOptionsEndpoint(new MockHttpResponseMethodOptions());
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nAllow: GET,PUT,OPTIONS,HEAD,POST\r\n";

        byte[] byteArray = subject.route(httpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns405Response_IfNo_MethodMatch() {
        MethodOptionsEndpoint subject = new MethodOptionsEndpoint(new MockHttpResponseMethodOptions());
        MockHttpRequestNoMatch httpRequest = new MockHttpRequestNoMatch();
        String expectedRouteResponse = "HTTP/1.1 405 Method Not Allowed\r\n";

        byte[] byteArray = subject.route(httpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}