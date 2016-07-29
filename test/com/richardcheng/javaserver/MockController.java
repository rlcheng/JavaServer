package com.richardcheng.javaserver;

import com.richardcheng.endpoint.IEndpoint;

public class MockController extends Controller {
    public MockController(IEndpoint[] endpoints) {
        super(endpoints);
    }

    public String routeRequest(HttpRequest request) {
        return "response";
    }
}
