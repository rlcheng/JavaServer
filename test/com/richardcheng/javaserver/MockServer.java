package com.richardcheng.javaserver;

import java.net.Socket;

public class MockServer extends Server {
    int iterator;
    boolean stopCalled;
    boolean createCalled;
    boolean acceptCalled;
    boolean requestCalled;
    boolean responseCalled;
    private Controller controller;
    private HttpRequest request;
    private Socket requestSocket;

    public MockServer(Controller controller, HttpRequest request) {
        super(controller, request);
        this.controller = controller;
        this.request = request;
    }

    public void run(int port) {
        this.create(port);

        iterator = 0;
        while (iterator < 10) {
            requestSocket = this.accept();
            this.request();
            this.response();
            iterator++;
        }
    }

    public void stop() {
        stopCalled = true;
    }

    private void create(int port) {
        createCalled = true;
    }

    private Socket accept() {
        acceptCalled = true;
        return new Socket();
    }

    private void request() {
        requestCalled = true;
    }

    private void response() {
        responseCalled = true;
    }
}
