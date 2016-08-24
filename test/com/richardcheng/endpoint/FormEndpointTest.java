package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.*;
import org.junit.Assert;
import org.junit.Test;

public class FormEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "form";
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingtoMatch";
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns200Response_IfPOSTMethodMatch_WithData() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestPost httpRequest = new MockHttpRequestPost();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 13\r\n\r\ndata=garfield";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_IfPUTMethodMatch_WithData() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestPut httpRequest = new MockHttpRequestPut();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 13\r\n\r\ndata=garfield";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_OnlyIfGETMethod() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 0\r\n\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns405Response_IfNo_MethodMatch() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestNoMatch httpRequest = new MockHttpRequestNoMatch();
        String expectedRouteResponse = "HTTP/1.1 405 Method Not Allowed\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_Only_IfDELETEMethod() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestDelete httpRequest = new MockHttpRequestDelete();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}