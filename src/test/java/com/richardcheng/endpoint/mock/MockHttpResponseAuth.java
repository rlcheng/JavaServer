package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpResponse;

public class MockHttpResponseAuth extends HttpResponse{
    public String statusLine(String code) {
        return "HTTP/1.1 401 Unauthorized\r\n";
    }

    public String authHeader() {
        return "WWW-Authenticate: Basic\r\n";
    }

    public String completeResponse(String code, String message) {
        return "HTTP/1.1 200 OK\r\nGET /log HTTP/1.1\r\n";
    }
}
