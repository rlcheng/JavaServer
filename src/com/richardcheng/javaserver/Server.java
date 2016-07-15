package com.richardcheng.javaserver;

import java.net.*;
import java.io.*;

/**
 * Created by richardcheng on 7/13/16.
 */
public class Server {
    private int port;
    private ServerSocket serverSocket;
    private Socket connectionSocket;

    public Server(int p) {
        port = p;
    }

    public int getPort() {
        return port;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public String read() throws IOException {
        connectionSocket = serverSocket.accept();
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        String request = fromClient.readLine();
        return request;
    }

    public void write() throws IOException {
        DataOutputStream toClient = new DataOutputStream(connectionSocket.getOutputStream());
        toClient.writeBytes("HTTP/1.1 200 OK\n");
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
}
