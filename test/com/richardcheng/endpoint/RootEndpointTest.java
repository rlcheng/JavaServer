package com.richardcheng.endpoint;

import org.junit.Test;
import org.junit.Assert;

public class RootEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "root";
        RootEndpoint subject = new RootEndpoint();

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingtoMatch";
        RootEndpoint subject = new RootEndpoint();

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_ReturnsResponse() {
        RootEndpoint subject = new RootEndpoint();
        String httpMethod = "GET";
        String expectedRouteResponse = "HTTP/1.1 200 OK\n";

        String actualRouteResponse = subject.route(httpMethod);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}