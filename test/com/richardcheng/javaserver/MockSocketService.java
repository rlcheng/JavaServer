package com.richardcheng.javaserver;

import java.net.Socket;

public class MockSocketService extends SocketService {
    boolean socketCreated;
    boolean socketAccepted;
    boolean socketClosed;
    boolean responseSent;

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
        return "message";
    }

    public void write(String message, Socket connectionSocket) {
        responseSent = true;
    }
}
