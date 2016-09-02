package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpResponse;

public class MockHttpResponseParameters extends HttpResponse{
    public String statusLine(String code) {
        return "HTTP/1.1 200 OK\r\n";
    }
}
