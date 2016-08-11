package com.richardcheng.endpoint;

import com.richardcheng.presenter.MockHttpResponse;
import org.junit.Assert;
import org.junit.Test;

public class MethodOptionsEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "method_options";
        MethodOptionsEndpoint subject = new MethodOptionsEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingtoMatch";
        MethodOptionsEndpoint subject = new MethodOptionsEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns200Response_IfMethodMatch() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponse());
        String httpMethod = "GET";
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";

        String actualRouteResponse = subject.route(httpMethod);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}