package com.richardcheng.javaserver;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by richardcheng on 7/14/16.
 */
public class ControllerTest {
    @Test
    public void testControllerStart() {
        MockSocketService socket = new MockSocketService();
        int port = 5000;
        Controller subject = new Controller(socket);

        subject.start(port);

        Assert.assertTrue(socket.socketCreated);
        Assert.assertTrue(socket.socketAccepted);
    }

    @Test
    public void testControllerStop() {
        MockSocketService socket = new MockSocketService();
        Controller subject = new Controller(socket);

        subject.stop();

        Assert.assertTrue(socket.socketClosed);
    }

    @Test
    public void testControllerRead_GET_Request() {
        String expectedRequest = "GET / HTTP/1.1";
        MockSocketService socket = new MockSocketService();
        socket.request = expectedRequest;
        Controller subject = new Controller(socket);

        subject.read();

        Assert.assertEquals(expectedRequest, subject.read());
    }

    @Test
    public void testControllerWrite_200_Response() {
        String expectedResponse = "HTTP/1.1 200 OK\n";
        MockSocketService socket = new MockSocketService();
        Controller subject = new Controller(socket);

        subject.write();

        Assert.assertTrue(socket.responseSent);
        Assert.assertEquals(expectedResponse, socket.responseMessage);
    }
}
