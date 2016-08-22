package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpResponseRoot;
import com.richardcheng.endpoint.mock.MockHttpResponseNotFound;
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
    public void match_ReturnsFalse_if_StringMatch() {
        String endpoint = "notGoingToMatch";
        RootEndpoint subject = new RootEndpoint(new MockHttpResponseNotFound(), new Presenter());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns200Response_IfGETRequestMethod_IsFound() {
        RootEndpoint subject = new RootEndpoint(new MockHttpResponseRoot(), new Presenter());
        String httpMethod = "GET";
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";
        String noData = null;

        String actualRouteResponse = subject.route(httpMethod, noData);

        Assert.assertTrue(actualRouteResponse.contains(expectedRouteResponse));
        Assert.assertTrue(actualRouteResponse.contains("href"));
        Assert.assertTrue(actualRouteResponse.contains("file1"));
    }

    @Test
    public void route_Returns200Response_IfHEADRequestMethod_IsFound() {
        RootEndpoint subject = new RootEndpoint(new MockHttpResponseRoot(), new Presenter());
        String httpMethod = "HEAD";
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";
        String noData = null;

        String actualRouteResponse = subject.route(httpMethod, noData);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }

    @Test
    public void route_Returns405Response_IfRequestMethod_NotFound() {
        RootEndpoint subject = new RootEndpoint(new MockHttpResponseRoot(), new Presenter());
        String httpMethod = "PUT";
        String expectedRouteResponse = "HTTP/1.1 405 Method Not Allowed\r\n";
        String noData = null;

        String actualRouteResponse = subject.route(httpMethod, noData);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}