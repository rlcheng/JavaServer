package com.richardcheng.javaserver;

import java.net.Socket;

public class Server {
    private SocketService socketService;
    private Controller controller;
    private HttpRequest request;
    private String response;
    private Socket requestSocket;

    public Server(SocketService socketService, Controller controller) {
        this.socketService = socketService;
        this.controller = controller;
    }

    public void start(int port) {
        socketService.create(port);
        requestSocket = socketService.accept();
    }

    public void stop() {
        socketService.close();
    }

    public HttpRequest request() {
        String requestString = socketService.parseSocketMessage(requestSocket);
        request = new HttpRequest();
        request.parseRequest(requestString);
        return request;
    }

    public void response() {
        response = controller.routeRequest(request);
        socketService.write(response, requestSocket);
    }
}
