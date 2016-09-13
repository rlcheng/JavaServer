package com.richardcheng.javaserver;

import com.richardcheng.javaserver.mock.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class ServerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void run_LoopsThrough_Accept_Request_Response_Methods() {
        MockServerSocket mockServerSocket;
        try {
            mockServerSocket = new MockServerSocket();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        MockController mockController = new MockController(null);
        MockHttpRequest mockHttpRequest = new MockHttpRequest();
        Server subject = new Server(new ShouldLoop10Times(), mockServerSocket, mockController, mockHttpRequest);
        int port = 5000;

        subject.run(port);

        Assert.assertTrue(mockServerSocket.setReuseAddressCalled);
        Assert.assertTrue(mockServerSocket.bindCalled);
        Assert.assertTrue(mockHttpRequest.requestParsed);
    }

    @Test
    public void run_LoopsThrough_IfAcceptMethodGood_RequestFails_andSkipsResponse() {
        MockServerSocket mockServerSocket;
        try {
            mockServerSocket = new MockServerSocket();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        MockController mockController = new MockController(null);
        MockHttpRequestReturnFalse mockHttpRequest = new MockHttpRequestReturnFalse();
        Server subject = new Server(new ShouldLoop10Times(), mockServerSocket, mockController, mockHttpRequest);
        int port = 5000;

        subject.run(port);

        Assert.assertTrue(mockServerSocket.setReuseAddressCalled);
        Assert.assertTrue(mockServerSocket.bindCalled);
        Assert.assertFalse(mockHttpRequest.requestParsed);
    }

    @Test
    public void run_serverSocket_bind_throwsException() {
        MockServerSocketBindThrowsException mockServerSocket;
        try {
            mockServerSocket = new MockServerSocketBindThrowsException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        MockController mockController = new MockController(null);
        MockHttpRequest mockHttpRequest = new MockHttpRequest();
        Server subject = new Server(new ShouldLoop10Times(), mockServerSocket, mockController, mockHttpRequest);
        int port = 5000;

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("something went wrong");
        subject.run(port);
    }

    @Test
    public void run_serverSocket_accept_throwsException() {
        MockServerSocketAcceptThrowsException mockServerSocket;
        try {
            mockServerSocket = new MockServerSocketAcceptThrowsException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        MockController mockController = new MockController(null);
        MockHttpRequest mockHttpRequest = new MockHttpRequest();
        Server subject = new Server(new ShouldLoop10Times(), mockServerSocket, mockController, mockHttpRequest);
        int port = 5000;

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("something went wrong");
        subject.run(port);
    }

    @Test
    public void run_request_throwsException() {
        MockServerSocketForRequest mockServerSocket;
        try {
            mockServerSocket = new MockServerSocketForRequest();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        MockController mockController = new MockController(null);
        MockHttpRequest mockHttpRequest = new MockHttpRequest();
        Server subject = new Server(new ShouldLoop10Times(), mockServerSocket, mockController, mockHttpRequest);
        int port = 5000;

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("something went wrong");
        subject.run(port);
    }

    @Test
    public void run_response_throwsException() {
        MockServerSocketForResponse mockServerSocket;
        try {
            mockServerSocket = new MockServerSocketForResponse();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        MockController mockController = new MockController(null);
        MockHttpRequest mockHttpRequest = new MockHttpRequest();
        Server subject = new Server(new ShouldLoop10Times(), mockServerSocket, mockController, mockHttpRequest);
        int port = 5000;

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("something went wrong");
        subject.run(port);
    }

    @Test
    public void stop_throwsException() {
        MockServerSocketForStop mockServerSocket;
        try {
            mockServerSocket = new MockServerSocketForStop();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        MockController mockController = new MockController(null);
        MockHttpRequest mockHttpRequest = new MockHttpRequest();
        Server subject = new Server(new ShouldLoop10Times(), mockServerSocket, mockController, mockHttpRequest);
        int port = 5000;

        thrown.expect(RuntimeException.class);
        subject.run(port);
    }
}
