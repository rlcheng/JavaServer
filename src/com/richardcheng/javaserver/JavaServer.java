package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/15/16.
 */
public class JavaServer {
    public static void main(String[] args) {
        SocketService socket = new SocketService();
        Controller controller = new Controller(socket);
        int port = 5000;

        controller.start(port);
        String request = controller.read();
        System.out.println("Request: " + request);
        controller.write();
        controller.stop();
    }
}
