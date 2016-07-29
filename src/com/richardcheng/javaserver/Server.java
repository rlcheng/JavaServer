package com.richardcheng.javaserver;

import java.net.Socket;

public class Server {
    private SocketService socketService;
    private Controller controller;
    private HttpRequest request;
    private String response;
    private Socket requestSocket;

    public Server(SocketService socketService, Controller controller, HttpRequest request) {
        this.socketService = socketService;
        this.controller = controller;
        this.request = request;
    }

    public void start(int port) {
        socketService.create(port);
        requestSocket = socketService.accept();
    }

    public void stop() {
        socketService.close();
    }

    public void request() {
        String requestString = socketService.parseSocketMessage(requestSocket);
        request.parseRequest(requestString);
    }

    public void response() {
        response = controller.routeRequest(request);
        socketService.write(response, requestSocket);
    }
}
