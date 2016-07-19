package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/18/16.
 */
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

    public void accept() {
        socketAccepted = true;
    }

    public void close() {
        socketClosed = true;
    }

    public String getRequest() {
        return request;
    }

    public void sendResponse(String message) {
        responseSent = true;
        responseMessage = message;
    }
}
