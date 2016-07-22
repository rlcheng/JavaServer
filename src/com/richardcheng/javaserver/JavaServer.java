package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/15/16.
 */
public class JavaServer {
    public static void main(String[] args) {
        SocketService socket = new SocketService();
        Controller controller = new Controller();
        Server server = new Server(socket, controller);
        int port = 5000;

        server.start(port);
        HttpRequest request = server.request();
        System.out.println("Request: " + request.getMethod() + " " + request.getUri() + " " + request.getVersion());
        server.response();
        server.stop();
    }
}
