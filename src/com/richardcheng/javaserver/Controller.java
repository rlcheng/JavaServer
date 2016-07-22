package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/19/16.
 */
public class Controller implements IController {
    public String routeRequest(String request) {
        return "HTTP/1.1 200 OK\n";
    }
}
