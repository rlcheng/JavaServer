package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestDelete extends HttpRequest {
    public String getMethod() {
        return "DELETE";
    }

    public String getData() {
        return "data=garfield";
    }
}
