package com.richardcheng.javaserver;

public class MockHttpRequest extends HttpRequest {
    boolean requestParsed;

    public void parseRequest(String request) {
        requestParsed = true;
    }

    public String getEndpoint() {
        return "endpoint";
    }
}
