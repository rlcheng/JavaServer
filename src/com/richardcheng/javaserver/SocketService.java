package com.richardcheng.javaserver;

import java.net.*;
import java.io.*;

public class SocketService implements ISocketService {
    private ServerSocket serverSocket;

    public void create(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket accept() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close()
    {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String parseSocketMessage(Socket connectionSocket) {
        try {
            BufferedReader requestMessage = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            String request = requestMessage.readLine();
            return request;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String message, Socket connectionSocket) {
        try {
            DataOutputStream response = new DataOutputStream(connectionSocket.getOutputStream());
            response.writeBytes(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
