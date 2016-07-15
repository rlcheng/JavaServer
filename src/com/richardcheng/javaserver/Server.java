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

    public Server(int p) {
        port = p;
    }

    public int getPort() {
        return port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
        }
        catch(IOException e) {
            throw new RuntimeModelerException(e);
        }
    }

    public String read() {
        String request;
        try {
            connectionSocket = serverSocket.accept();
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            request = fromClient.readLine();
        }
        catch(IOException e){
            throw new RuntimeModelerException(e);
        }

        return request;
    }

    public void write() {
        try {
            DataOutputStream toClient = new DataOutputStream(connectionSocket.getOutputStream());
            toClient.writeBytes("HTTP/1.1 200 OK\n");
        }
        catch(IOException e) {
            throw new RuntimeModelerException(e);
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
