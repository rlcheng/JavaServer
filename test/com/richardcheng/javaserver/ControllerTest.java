package com.richardcheng.javaserver;

import com.richardcheng.endpoint.IEndpoint;
import com.richardcheng.presenter.MockHttpResponse;
import com.richardcheng.presenter.Presenter;
import org.junit.Assert;
import org.junit.Test;

public class ControllerTest {
    @Test
    public void testControllerRouteResponse() {
        MockHttpRequest request = new MockHttpRequest();
        IEndpoint[] endpoints = { new MockRootEndpoint(new MockHttpResponse(), new Presenter()) };
        Controller subject = new Controller(endpoints);
        String expectedResponse = "OK";

        String actualResponse = subject.routeRequest(request);

        Assert.assertEquals(expectedResponse, actualResponse);
    }
}
