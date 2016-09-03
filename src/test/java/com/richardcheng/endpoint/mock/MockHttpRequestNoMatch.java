package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestNoMatch extends HttpRequest {
    public String getMethod() {
        return "NOMATCH";
    }
}
