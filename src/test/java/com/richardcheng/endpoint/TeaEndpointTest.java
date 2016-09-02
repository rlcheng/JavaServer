package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpRequestPost;
import com.richardcheng.httpIO.mock.MockHttpResponse;
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
    public void match_ReturnsFalse_if_StringNotMatch() {
        String endpoint = "notGoingtoMatch";
        TeaEndpoint subject = new TeaEndpoint(new MockHttpResponse());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_ReturnsResponse() {
        TeaEndpoint subject = new TeaEndpoint(new MockHttpResponse());
        MockHttpRequestPost httpRequest = new MockHttpRequestPost();
        String expectedRouteResponse = "HTTP/1.1 200 OK\r\n";

        byte[] byteArray = subject.route(httpRequest);
        String actualRouteResponse = new String(byteArray);

        Assert.assertEquals(expectedRouteResponse, actualRouteResponse);
    }
}