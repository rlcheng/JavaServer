package com.richardcheng.endpoint;

import com.richardcheng.presenter.HttpResponse;

import java.util.Hashtable;

public class MockHttpResponseMethodOptions extends HttpResponse {
    public String statusLine(String code) {
        if (code.equals("200")) {
            return "HTTP/1.1 200 OK\r\n";
        }
        return "HTTP/1.1 405 Method Not Allowed\r\n";
    }

    public String allowHeader(Hashtable<String, String> allowedMethods) {
        return "Allow: GET,PUT,OPTIONS,HEAD,POST\r\n";
    }
}
