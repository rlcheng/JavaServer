package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpRequestGet;
import com.richardcheng.endpoint.mock.MockHttpResponseCoffee;
import com.richardcheng.httpIO.mock.MockHttpResponse;
import org.junit.Assert;
import org.junit.Test;

public class CoffeeEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "coffee";
        CoffeeEndpoint subject = new CoffeeEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringNotMatch() {
        String endpoint = "notGoingtoMatch";
        CoffeeEndpoint subject = new CoffeeEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_ReturnsResponse() {
        CoffeeEndpoint subject = new CoffeeEndpoint(new MockHttpResponseCoffee());
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String expectedBodyLength = "12";

        byte[] byteArray = subject.route(httpRequest);

        String actualRouteResponse = new String(byteArray);

        Assert.assertTrue(actualRouteResponse.contains("HTTP/1.1 418 I'm a teapot"));
        Assert.assertTrue(actualRouteResponse.contains(expectedBodyLength));
    }
}