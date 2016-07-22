package com.richardcheng.javaserver;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by richardcheng on 7/19/16.
 */
public class ControllerTest {
    @Test
    public void testControllerRouteResponse_Root() {
        String expectedResponse = "HTTP/1.1 200 OK\n";
        HttpRequest request = new HttpRequest();
        request.parseRequest("GET / HTTP/1.1");
        Controller subject = new Controller();

        String actualResponse = subject.routeRequest(request);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testControllerRouteResponse_Form() {
        String expectedResponse = "HTTP/1.1 200 OK\n";
        HttpRequest request = new HttpRequest();
        request.parseRequest("GET /form HTTP/1.1");
        Controller subject = new Controller();

        String actualResponse = subject.routeRequest(request);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testControllerRouteResponse_NoRouteFound() {
        String expectedResponse = "HTTP/1.1 404 NOT FOUND\n";
        HttpRequest request = new HttpRequest();
        request.parseRequest("GET /MIA HTTP/1.1");
        Controller subject = new Controller();

        String actualResponse = subject.routeRequest(request);

        Assert.assertEquals(expectedResponse, actualResponse);
    }
}