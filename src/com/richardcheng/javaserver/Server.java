package com.richardcheng.javaserver;

import java.net.Socket;

/**
 * Created by richardcheng on 7/13/16.
 */
public class Server {
    private ISocketService socketService;
    private IController controller;
    private String request;
    private String response;
    private Socket requestSocket;

    public Server(ISocketService socketService, IController controller) {
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

    public String request() {
        request = socketService.parse(requestSocket);
        return request;
    }

    public void response() {
        response = controller.routeRequest(request);
        socketService.write(response, requestSocket);
    }
}
