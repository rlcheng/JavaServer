package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpResponseNotFound;
import org.junit.Assert;
import org.junit.Test;

public class InvalidEndpointTest {
    @Test
    public void match_ReturnsTrue() {
        String endpoint = "AnyString";
        InvalidEndpoint subject = new InvalidEndpoint(new MockHttpResponseNotFound());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void route_ReturnsResponse() {
        InvalidEndpoint subject = new InvalidEndpoint(new MockHttpResponseNotFound());
        String httpMethod = "GET";
        String expectedRouteResponse = "HTTP/1.1 404 Not Found\r\n";
        String noData = null;

        String actualRouteResponse = subject.route(httpMethod, noData);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}