package com.richardcheng.javaserver.mock;

import com.richardcheng.httpIO.HttpRequest;

import java.io.BufferedReader;

public class MockHttpRequestReturnFalse extends HttpRequest {
    public boolean requestParsed;

    public boolean parseMessage(BufferedReader requestMessage) {
        requestParsed = false;

        return false;
    }
}
