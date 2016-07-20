package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/13/16.
 */
public class Server {
    private ISocketService socket;
    private IController controller;
    private String request;
    private String response;

    public Server(ISocketService socket, IController controller) {
        this.socket = socket;
        this.controller = controller;
    }

    public void start(int port) {
        socket.create(port);
        socket.accept();
    }

    public void stop() {
        socket.close();
    }

    public String request() {
        request = socket.read();
        return request;
    }

    public void response() {
        response = controller.route(request);
        socket.write(response);
    }
}
