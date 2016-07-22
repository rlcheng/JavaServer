package com.richardcheng.javaserver;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by richardcheng on 7/19/16.
 */
public class ControllerTest {
    @Test
    public void testControllerRoute()
    {
        String expectedResponse = "HTTP/1.1 200 OK\n";
        String request = "GET";
        Controller subject = new Controller();

        String actualResponse = subject.routeRequest(request);

        Assert.assertEquals(expectedResponse, actualResponse);
    }
}