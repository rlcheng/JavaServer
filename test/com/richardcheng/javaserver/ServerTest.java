package com.richardcheng.javaserver;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by richardcheng on 7/14/16.
 */
public class ServerTest {
    @Test
    public void testServerStart() {
        MockSocketService socket = new MockSocketService();
        MockController controller = new MockController();
        Server subject = new Server(socket, controller);
        int port = 5000;

        subject.start(port);

        Assert.assertTrue(socket.socketCreated);
        Assert.assertTrue(socket.socketAccepted);
    }

    @Test
    public void testServerStop() {
        MockSocketService socket = new MockSocketService();
        MockController controller = new MockController();
        Server subject = new Server(socket, controller);

        subject.stop();

        Assert.assertTrue(socket.socketClosed);
    }

    @Test
    public void testServerRequest() {
        String expectedRequest = "GET / HTTP/1.1";
        MockSocketService socket = new MockSocketService();
        socket.request = expectedRequest;
        MockController controller = new MockController();
        Server subject = new Server(socket, controller);
        String actualRequest;

        actualRequest = subject.request();

        Assert.assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void testServerResponse() {
        String request = "GET / HTTP/1.1";
        String expectedResponse = "response";
        MockSocketService socket = new MockSocketService();
        socket.request = request;
        MockController controller = new MockController();
        Server subject = new Server(socket, controller);

        String test = subject.request();
        System.out.println(test);
        subject.response();

        Assert.assertTrue(socket.responseSent);
        Assert.assertEquals(expectedResponse, socket.responseMessage);
    }
}
