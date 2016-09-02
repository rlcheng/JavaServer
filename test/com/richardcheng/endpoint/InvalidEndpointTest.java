package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpRequestGet;
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
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String expectedRouteResponse = "HTTP/1.1 404 Not Found\r\n";

        String actualRouteResponse = subject.route(httpRequest).toString();

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}