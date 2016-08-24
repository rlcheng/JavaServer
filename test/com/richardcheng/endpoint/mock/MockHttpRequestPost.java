package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestPost extends HttpRequest{
    public String getMethod() {
        return "POST";
    }

    public String getData() {
        return "data=garfield";
    }
}
