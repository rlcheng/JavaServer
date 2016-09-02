package com.richardcheng.endpoint;

import com.richardcheng.endpoint.mock.MockHttpRequestGet;
import com.richardcheng.endpoint.mock.MockHttpResponseParameters;
import com.richardcheng.javaserver.mock.MockHttpRequest;
import org.junit.Assert;
import org.junit.Test;

public class ParametersEndpointTest {
    @Test
    public void match_ReturnsTrue_if_StringMatch() {
        String endpoint = "parameters";
        ParametersEndpoint subject = new ParametersEndpoint(new MockHttpResponseParameters());

        boolean actual = subject.match(endpoint);

        Assert.assertTrue(actual);
    }

    @Test
    public void match_ReturnsFalse_if_StringNotMatch() {
        String endpoint = "notGoingtoMatch";
        ParametersEndpoint subject = new ParametersEndpoint(new MockHttpResponseParameters());

        boolean actual = subject.match(endpoint);

        Assert.assertFalse(actual);
    }

    @Test
    public void route_Returns200() {
        ParametersEndpoint subject = new ParametersEndpoint(new MockHttpResponseParameters());
        MockHttpRequestGet httpRequest = new MockHttpRequestGet();
        String expectedResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 2\r\n\r\n\r\n";

        String actualResponse = subject.route(httpRequest);

        Assert.assertEquals(expectedResponse, actualResponse);
    }
}