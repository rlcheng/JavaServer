package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpResponse;

public class MockHttpResponseRedirect extends HttpResponse {
    public String statusLine(String code) {
        return "HTTP/1.1 302 Found\r\n";
    }

    public String location(int port) {
        return "Location: http://localhost:" + Integer.toString(port) + "/\r\n";
    }
}
