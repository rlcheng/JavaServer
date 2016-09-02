package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpResponse;

public class MockHttpResponsePartial extends HttpResponse {
    public String statusLine(String code) {
        if (code.equals("206")) {
            return "HTTP/1.1 206 Partial Content\r\n";
        }

        if (code.equals("200")) {
            return "HTTP/1.1 200 OK\r\n";
        }

        return "HTTP/1.1 405 Method Not Allowed\r\n";
    }
}
