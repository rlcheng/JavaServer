package com.richardcheng.endpoint;

import com.richardcheng.httpIO.mock.MockHttpResponse;
import org.junit.Assert;
import org.junit.Test;

public class FormEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "form";
        FormEndpoint subject = new FormEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingtoMatch";
        FormEndpoint subject = new FormEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_ReturnsResponse() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponse());
        String httpMethod = "POST";
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";

        String actualRouteResponse = subject.route(httpMethod);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}