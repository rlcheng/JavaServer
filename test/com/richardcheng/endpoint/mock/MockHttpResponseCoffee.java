package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpResponse;

public class MockHttpResponseCoffee extends HttpResponse {
    public String completeResponse(String code, String message) {
        String complete = "HTTP/1.1 418 I'm a teapot\r\n";
        complete += "Content-Type: text/html\r\nContent-Length: 12\r\n";
        complete += "\n";
        complete += "I'm a teapot\n";
        return complete;
    }
}
