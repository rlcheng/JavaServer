package com.richardcheng.javaserver.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequest extends HttpRequest {
    public boolean requestParsed;

    public void parseRequest(String request) {
        requestParsed = true;
    }

    public String getEndpoint() {
        return "endpoint";
    }
}
