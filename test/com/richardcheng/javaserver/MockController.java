package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/19/16.
 */
public class MockController implements IController {
    @Override
    public String routeRequest(String request) {
        return "response";
    }
}
