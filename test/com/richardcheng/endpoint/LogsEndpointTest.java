package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpRequestGet;
import com.richardcheng.endpoint.mock.MockHttpRequestGetNoAuth;
import com.richardcheng.endpoint.mock.MockHttpResponseAuth;
import com.richardcheng.httpIO.mock.MockHttpResponse;
import com.richardcheng.javaserver.mock.MockHttpRequest;
import org.junit.Assert;
import org.junit.Test;

public class LogsEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "logs";
        LogsEndpoint subject = new LogsEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingtoMatch";
        LogsEndpoint subject = new LogsEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns200withLogResponse_if_Authenticated() {
        LogsEndpoint subject = new LogsEndpoint(new MockHttpResponseAuth());
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nGET /log HTTP/1.1\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns401withAuthHeaderResponse_if_notAuthenticated() {
        LogsEndpoint subject = new LogsEndpoint(new MockHttpResponseAuth());
        MockHttpRequestGetNoAuth httpRequest = new MockHttpRequestGetNoAuth();
        String expectedRouteResponse = "HTTP/1.1 401 Unauthorized\r\nWWW-Authenticate: Basic\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}