package com.richardcheng.endpoint;

import com.richardcheng.presenter.HttpResponse;

public class MockHttpResponseNotFound extends HttpResponse {
    public String statusLine(String code) {
        return "HTTP/1.1 404 Not Found\r\n";
    }
}
