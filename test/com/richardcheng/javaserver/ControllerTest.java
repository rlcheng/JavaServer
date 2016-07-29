package com.richardcheng.javaserver;

import com.richardcheng.endpoint.IEndpoint;
import org.junit.Test;
import org.junit.Assert;

public class ControllerTest {
    @Test
    public void testControllerRouteResponse() {
        MockHttpRequest request = new MockHttpRequest();
        IEndpoint[] endpoints = { new MockRootEndpoint() };
        Controller subject = new Controller(endpoints);
        String expectedResponse = "OK";

        String actualResponse = subject.routeRequest(request);

        Assert.assertEquals(expectedResponse, actualResponse);
    }
}
