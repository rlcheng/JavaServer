package com.richardcheng.javaserver;

import org.junit.Test;
import org.junit.Assert;

public class ServerTest {
    @Test
    public void testServerStart() {
        MockSocketService socketService = new MockSocketService();
        MockController controller = new MockController(null);
        MockHttpRequest httpRequest = new MockHttpRequest();
        Server subject = new Server(socketService, controller, httpRequest);
        int port = 5000;

        subject.start(port);

        Assert.assertTrue(socketService.socketCreated);
        Assert.assertTrue(socketService.socketAccepted);
    }

    @Test
    public void testServerStop() {
        MockSocketService socketService = new MockSocketService();
        MockController controller = new MockController(null);
        MockHttpRequest httpRequest = new MockHttpRequest();
        Server subject = new Server(socketService, controller, httpRequest);

        subject.stop();

        Assert.assertTrue(socketService.socketClosed);
    }

    @Test
    public void testServerRequest() {
        MockSocketService socketService = new MockSocketService();
        MockController controller = new MockController(null);
        MockHttpRequest httpRequest = new MockHttpRequest();
        Server subject = new Server(socketService, controller, httpRequest);

        subject.request();

        Assert.assertTrue(httpRequest.requestParsed);
    }

    @Test
    public void testServerResponse() {
        MockSocketService socketService = new MockSocketService();
        MockController controller = new MockController(null);
        MockHttpRequest httpRequest = new MockHttpRequest();
        Server subject = new Server(socketService, controller, httpRequest);

        subject.response();

        Assert.assertTrue(socketService.responseSent);
    }
}
