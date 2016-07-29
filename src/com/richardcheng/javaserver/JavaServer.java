package com.richardcheng.javaserver;

public class JavaServer {
    public static void main(String[] args) {
        SocketService socket = new SocketService();
        Controller controller = new Controller();
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
