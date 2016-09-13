package com.richardcheng.javaserver.mock;

import com.richardcheng.httpIO.HttpRequest;

import java.io.BufferedReader;

public class MockHttpRequest extends HttpRequest {
    public boolean requestParsed;

    public boolean parseMessage(BufferedReader requestMessage) {
        requestParsed = true;

        return true;
    }

    public String getMethod() {
        return "GET";
    }

    public String getEndpoint() {
        return "endpoint";
    }

    public String getData() { return "data"; }
}
