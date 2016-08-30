package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.*;
import com.richardcheng.javaserver.mock.MockHttpRequest;
import com.richardcheng.presenter.Presenter;
import org.junit.Assert;
import org.junit.Test;

public class RootEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "root";
        RootEndpoint subject = new RootEndpoint(new MockHttpResponseRoot(), new Presenter());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringNotMatch() {
        String endpoint = "notGoingToMatch";
        RootEndpoint subject = new RootEndpoint(new MockHttpResponseNotFound(), new Presenter());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns200Response_IfGETRequestMethod_IsFound() {
        RootEndpoint subject = new RootEndpoint(new MockHttpResponseRoot(), new Presenter());
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertTrue(actualRouteResponse.contains(expectedRouteResponse));
        Assert.assertTrue(actualRouteResponse.contains("href"));
        Assert.assertTrue(actualRouteResponse.contains("file1"));
    }

    @Test
    public void route_Returns200Response_IfHEADRequestMethod_IsFound() {
        RootEndpoint subject = new RootEndpoint(new MockHttpResponseRoot(), new Presenter());
        MockHttpRequestHead httpRequest = new MockHttpRequestHead();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns405Response_IfRequestMethod_NotFound() {
        RootEndpoint subject = new RootEndpoint(new MockHttpResponseRoot(), new Presenter());
        MockHttpRequestPut httpRequest = new MockHttpRequestPut();
        String expectedRouteResponse = "HTTP/1.1 405 Method Not Allowed\r\n";

        String actualRouteResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}