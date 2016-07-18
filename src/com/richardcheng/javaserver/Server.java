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

    public Server(int p) { port = p; }

    public int getPort() { return port; }

    public void start() {
        serverSocket = createSocket(port);
    }

    protected ServerSocket createSocket(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        String request;

        acceptConnection();
        request = getRequest();

        return request;
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
            BufferedReader requestMessage = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            return requestMessage.readLine();
        } catch (IOException e) {
            throw new RuntimeModelerException(e);
        }
    }

    public void write() {
        String message = "HTTP/1.1 200 OK";
        sendResponse(message);
    }

    protected void sendResponse(String message) {
        try {
            DataOutputStream response = new DataOutputStream(connectionSocket.getOutputStream());
            response.writeBytes(message);
        } catch (IOException e) {
            throw new RuntimeModelerException(e);
        }
    }
}
