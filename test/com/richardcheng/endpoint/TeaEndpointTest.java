package com.richardcheng.endpoint;

import com.richardcheng.presenter.MockHttpResponse;
import org.junit.Assert;
import org.junit.Test;

public class TeaEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "tea";
        TeaEndpoint subject = new TeaEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingtoMatch";
        TeaEndpoint subject = new TeaEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_ReturnsResponse() {
        TeaEndpoint subject = new TeaEndpoint(new MockHttpResponse());
        String httpMethod = "POST";
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";

        String actualRouteResponse = subject.route(httpMethod);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}