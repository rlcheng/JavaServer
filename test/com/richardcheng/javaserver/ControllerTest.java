package com.richardcheng.javaserver;

import com.richardcheng.endpoint.IEndpoint;
import com.richardcheng.javaserver.mock.MockHttpRequest;
import com.richardcheng.javaserver.mock.MockRootEndpoint;
import com.richardcheng.javaserver.mock.MockRootEndpointNoMatch;
import com.richardcheng.httpIO.mock.MockHttpResponse;
import com.richardcheng.presenter.Presenter;
import org.junit.Assert;
import org.junit.Test;

public class ControllerTest {
    @Test
    public void routeRequest_ReturnsRouteResponse_IfEndpointFound() {
        MockHttpRequest request = new MockHttpRequest();
        IEndpoint[] endpoints = { new MockRootEndpoint(new MockHttpResponse(), new Presenter()) };
        Controller subject = new Controller(endpoints);
        String expectedResponse = "OK";

        String actualResponse = subject.routeRequest(request);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void routeRequest_ReturnsNull_IfEndpointNotFound() {
        MockHttpRequest request = new MockHttpRequest();
        IEndpoint[] endpoints = { new MockRootEndpointNoMatch(new MockHttpResponse(), new Presenter()) };
        Controller subject = new Controller(endpoints);

        String actualResponse = subject.routeRequest(request);

        Assert.assertNull(actualResponse);
    }
}
