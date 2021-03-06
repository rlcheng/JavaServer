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
    public void match_ReturnsFalse_if_StringNotMatch() {
        String endpoint = "notGoingtoMatch";
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns200Response_IfPOSTMethodMatch_WithData_WhenDataEmpty() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestPost httpRequest = new MockHttpRequestPost();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 9\r\n\r\ndata=odey";

        byte[] byteArray = subject.route(httpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_IfPostMethodMatch_NoDataUpdate_WhenDataExist() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestPost postHttpRequest = new MockHttpRequestPost();
        MockHttpRequestPut putHttpRequest = new MockHttpRequestPut();
        MockHttpRequestPost secondPostHttpRequest = new MockHttpRequestPost();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 13\r\n\r\ndata=garfield";

        byte[] byteArray = subject.route(postHttpRequest);
        String firstRouteResponse = new String(byteArray);
        byteArray = subject.route(putHttpRequest);
        String secondRouteResponse = new String(byteArray);
        byteArray = subject.route(secondPostHttpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
        Assert.assertEquals(secondRouteResponse, actualRouteResponse);
        Assert.assertNotEquals(firstRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_IfPUTMethodMatch_WithData_WhenUpdatingData() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestPut httpRequest = new MockHttpRequestPut();
        MockHttpRequestPost httpRequestPost = new MockHttpRequestPost();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 13\r\n\r\ndata=garfield";

        byte[] byteArray = subject.route(httpRequestPost);

        String setupResponse = new String(byteArray);
        byteArray = subject.route(httpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
        Assert.assertNotEquals(setupResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_IfPUTMethodMatch_NoData_WhenNo_ExistingData() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestPut httpRequest = new MockHttpRequestPut();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 0\r\n\r\n";

        byte[] byteArray = subject.route(httpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_OnlyIfGETMethod() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 0\r\n\r\n";

        byte[] byteArray = subject.route(httpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns405Response_IfNo_MethodMatch() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestNoMatch httpRequest = new MockHttpRequestNoMatch();
        String expectedRouteResponse = "HTTP/1.1 405 Method Not Allowed\r\n";

        byte[] byteArray = subject.route(httpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns200Response_Only_IfDELETEMethod() {
        FormEndpoint subject = new FormEndpoint(new MockHttpResponseForm());
        MockHttpRequestDelete httpRequest = new MockHttpRequestDelete();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 0\r\n\r\n";

        byte[] byteArray = subject.route(httpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}