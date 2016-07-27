package com.richardcheng.javaserver;

import java.net.Socket;

/**
 * Created by richardcheng on 7/18/16.
 */
public interface ISocketService {
    void create(int port);
    Socket accept();
    void close();
    String parseSocketMessage(Socket connectionSocket);
    void write(String message, Socket connectionSocket);
}
