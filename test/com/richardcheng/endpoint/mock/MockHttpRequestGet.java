package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestGet extends HttpRequest {
    public String getMethod() {
        return "GET";
    }

    public String getData() {
        return "data=garfield";
    }

    public String getLog() {
        return "GET /log HTTP/1.1";
    }

    public String getAuth() {
        return "admin:hunter2";
    }
}
