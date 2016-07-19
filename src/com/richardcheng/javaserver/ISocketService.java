package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/18/16.
 */
public interface ISocketService {
    void create(int port);
    void accept();
    void close();
    String getRequest();
    void sendResponse(String message);
}
