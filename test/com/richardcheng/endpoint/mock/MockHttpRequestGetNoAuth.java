package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestGetNoAuth extends HttpRequest {
    public String getMethod() {
        return "GET";
    }

    public String getLog() {
        return "GET /log HTTP/1.1";
    }

    public String getAuth() {
        return "admin:who";
    }
}
