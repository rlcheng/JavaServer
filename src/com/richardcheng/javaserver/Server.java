package com.richardcheng.javaserver;

import java.net.*;
import java.io.*;

/**
 * Created by richardcheng on 7/13/16.
 */
public class Server {
    private int port;
    private ServerSocket serverSocket;

    public Server(int p) {
        port = p;
    }

    public int getPort() {
        return port;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public String read() {
        return "response";
    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
