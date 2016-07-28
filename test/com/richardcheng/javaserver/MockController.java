package com.richardcheng.javaserver;

public class MockController implements IController {
    @Override
    public String routeRequest(HttpRequest request) {
        return "response";
    }
}
