package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestPut extends HttpRequest {
    public String getMethod() {
        return "PUT";
    }

    public String getData() {
        return "data=garfield";
    }
}
