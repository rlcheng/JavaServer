package com.richardcheng.httpIO.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestRange extends HttpRequest{
    private String range = "";

    public void setRange(String range) {
        this.range = range;
    }
}
