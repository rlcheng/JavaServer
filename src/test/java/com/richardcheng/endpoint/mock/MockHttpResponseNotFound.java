package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpResponse;

public class MockHttpResponseNotFound extends HttpResponse {
    public String statusLine(String code) {
        return "HTTP/1.1 404 Not Found\r\n";
    }
}
