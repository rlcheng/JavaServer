package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpResponse;

import java.util.Hashtable;

public class MockHttpResponseMethodOptions2 extends HttpResponse{
    public String statusLine(String code) {
        if (code.equals("200")) {
            return "HTTP/1.1 200 OK\r\n";
        }
        return "HTTP/1.1 405 Method Not Allowed\r\n";
    }

    public String allowHeader(Hashtable<String, String> allowedMethods) {
        return "Allow: GET,OPTIONS\r\n";
    }
}
