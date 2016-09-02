package com.richardcheng.javaserver.mock;

import com.richardcheng.endpoint.IEndpoint;
import com.richardcheng.javaserver.Controller;
import com.richardcheng.httpIO.HttpRequest;

public class MockController extends Controller {
    public MockController(IEndpoint[] endpoints) {
        super(endpoints);
    }

    public String routeRequest(HttpRequest request) {
        return "response";
    }
}
