package com.richardcheng.endpoint;

import com.richardcheng.presenter.HttpResponse;

public class MockHttpResponseRedirect extends HttpResponse {
    public String statusLine(String code) {
        return "HTTP/1.1 302 Found\r\n";
    }

    public String location(int port) {
        return "Location: http://localhost:" + Integer.toString(port) + "/\r\n";
    }
}
