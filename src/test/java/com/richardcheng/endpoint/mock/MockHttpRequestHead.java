package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestHead extends HttpRequest {
    public String getMethod() {
        return "HEAD";
    }
}
