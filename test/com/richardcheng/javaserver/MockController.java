package com.richardcheng.javaserver;

public class MockController extends Controller {
    public String routeRequest(HttpRequest request) {
        return "response";
    }
}
