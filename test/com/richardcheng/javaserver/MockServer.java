package com.richardcheng.javaserver;

import java.net.Socket;

public class MockServer extends Server {
    boolean stopCalled;
    boolean createCalled;
    boolean acceptCalled;
    boolean requestCalled;
    boolean responseCalled;

    public MockServer(ShouldLoop serverRunShouldLoop, Controller controller, HttpRequest request) {
        super(serverRunShouldLoop, controller, request);
    }

    public void stop() {
        stopCalled = true;
    }

    protected void create(int port) {
        createCalled = true;
    }

    protected Socket accept() {
        acceptCalled = true;
        return new Socket();
    }

    protected void request() {
        requestCalled = true;
    }

    protected void response() {
        responseCalled = true;
    }
}
