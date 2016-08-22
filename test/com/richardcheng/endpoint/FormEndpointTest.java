package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpResponseForm;
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
        String httpMethod = "POST";
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 11\r\n\r\ndata=fatcat";

        String actualRouteResponse = subject.route(httpMethod, "data=fatcat");

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_IfPUTMethodMatch_WithData() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        String httpMethod = "PUT";
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 11\r\n\r\ndata=fatcat";

        String actualRouteResponse = subject.route(httpMethod, "data=fatcat");

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_OnlyIfGETMethod() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        String httpMethod = "GET";
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 0\r\n\r\n";

        String actualRouteResponse = subject.route(httpMethod, "data=fatcat");

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns405Response_IfNo_MethodMatch() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        String httpMethod = "NOMATCH";
        String expectedRouteResponse = "HTTP/1.1 405 Method Not Allowed\r\n";

        String actualRouteResponse = subject.route(httpMethod, "");

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_Only_IfDELETEMethod() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        String httpMethod = "DELETE";
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";

        String actualRouteResponse = subject.route(httpMethod, "");

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}