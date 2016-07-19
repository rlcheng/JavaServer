package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/13/16.
 */
public class Controller {
    private ISocketService socket;

    public Controller(ISocketService s) { socket = s; }

    public void start(int port) {
        socket.create(port);
        socket.accept();
    }

    public void stop() {
        socket.close();
    }

    public String read() {
        return socket.getRequest();
    }

    public void write() {
        String message = "HTTP/1.1 200 OK\n";
        socket.sendResponse(message);
    }
}
