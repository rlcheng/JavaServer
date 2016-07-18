package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/15/16.
 */
public class JavaServer {
    public static void main(String[] args) {
        Server server = new Server(5000);

        server.start();
        String request = server.read();
        System.out.println("Request: " + request);
        server.write();
        server.stop();
    }
}
