package com.richardcheng.javaserver;

import com.sun.xml.internal.ws.model.RuntimeModelerException;

import java.net.*;
import java.io.*;

/**
 * Created by richardcheng on 7/13/16.
 */
public class Server {
    private int port;
    private ServerSocket serverSocket;
    private Socket connectionSocket;

    public Server() { port = 5000; }

    public Server(int p) {
        port = p;
    }

    public int getPort() {
        return port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeModelerException(e);
        }
    }

    public String read() {
        String request;

        acceptConnection();
        request = getRequest();

        return request;
    }

    public void write() {
        try {
            DataOutputStream toClient = new DataOutputStream(connectionSocket.getOutputStream());
            toClient.writeBytes("HTTP/1.1 200 OK\n");
        } catch (IOException e) {
            throw new RuntimeModelerException(e);
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void acceptConnection() {
        try {
            connectionSocket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeModelerException(e);
        }
    }

    protected String getRequest() {
        try {
            DataInputStream requestStream = new DataInputStream(connectionSocket.getInputStream());
            return requestStream.toString();
        } catch (IOException e) {
            throw new RuntimeModelerException(e);
        }
    }
}
