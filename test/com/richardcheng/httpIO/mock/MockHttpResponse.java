package com.richardcheng.httpIO.mock;

import com.richardcheng.httpIO.HttpResponse;

public class MockHttpResponse extends HttpResponse {
    public String statusLine(String code) {
        return "HTTP/1.1 200 OK\r\n";
    }

    public String entityHeader(int bodyLength) {
        return "Content-Type: text/html\r\nContent-Length: 5\r\n";
    }
}
