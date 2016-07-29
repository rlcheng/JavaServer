package com.richardcheng.javaserver;
import com.richardcheng.endpoint.*;

public class JavaServer {
    public static void main(String[] args) {
        int port;
        String publicDirPath = null;
        if (args.length == 2) {
            port = Integer.parseInt(args[0]);
            publicDirPath = args[1];
        }
        else {
            port = 5000;
        }

        IEndpoint[] endpoints = { new RootEndpoint(publicDirPath), new FormEndpoint(), new InvalidEndpoint() };
        Controller controller = new Controller(endpoints);
        HttpRequest request = new HttpRequest();
        SocketService socket = new SocketService();
        Server server = new Server(socket, controller, request);

        server.start(port);
        server.request();
        System.out.println("Request: " + request.getMethod() + " " + request.getUri() + " " + request.getVersion());
        server.response();
        server.stop();
    }
}
