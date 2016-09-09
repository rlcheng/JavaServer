package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestGetOnly extends HttpRequest {
    public String getMethod() {
        return "GET";
    }
}
