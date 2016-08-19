package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpResponseRedirect;
import com.richardcheng.httpIO.mock.MockHttpResponse;
import org.junit.Assert;
import org.junit.Test;

public class RedirectEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "redirect";
        int port = 5000;
        RedirectEndpoint subject = new RedirectEndpoint(new MockHttpResponse(), port);

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingtoMatch";
        int port = 5000;
        RedirectEndpoint subject = new RedirectEndpoint(new MockHttpResponse(), port);

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_ReturnsResponse() {
        int port = 5000;
        RedirectEndpoint subject = new RedirectEndpoint(new MockHttpResponseRedirect(), port);
        String httpMethod = "GET";
        String expectedRouteResponse = "HTTP/1.1 302 Found\r\nLocation: http://localhost:5000/\r\n";

        String actualRouteResponse = subject.route(httpMethod);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

}