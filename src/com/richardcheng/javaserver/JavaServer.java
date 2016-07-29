package com.richardcheng.javaserver;
import com.richardcheng.endpoint.*;

public class JavaServer {
    public static void main(String[] args) {
        SocketService socket = new SocketService();
        IEndpoint[] endpoints = { new RootEndpoint(), new FormEndpoint(), new InvalidEndpoint() };
        Controller controller = new Controller(endpoints);
        HttpRequest request = new HttpRequest();
        Server server = new Server(socket, controller, request);
        int port = 5000;

        server.start(port);
        server.request();
        System.out.println("Request: " + request.getMethod() + " " + request.getUri() + " " + request.getVersion());
        server.response();
        server.stop();
    }
}
