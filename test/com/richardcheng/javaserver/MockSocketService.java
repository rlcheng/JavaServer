package com.richardcheng.javaserver;

import java.net.Socket;

public class MockSocketService implements ISocketService {
    boolean socketCreated;
    boolean socketAccepted;
    boolean socketClosed;
    String request;
    boolean responseSent;
    String responseMessage;

    public void create(int port) {
        socketCreated = true;
    }

    public Socket accept() {
        socketAccepted = true;
        return new Socket();
    }

    public void close() {
        socketClosed = true;
    }

    public String parseSocketMessage(Socket connectionSocket) {
        return request;
    }

    public void write(String message, Socket connectionSocket) {
        responseSent = true;
        responseMessage = message;
    }
}
