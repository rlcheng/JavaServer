package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/28/16.
 */
public class MockHttpRequest extends HttpRequest {
    boolean requestParsed;

    public void parseRequest(String request) {
        requestParsed = true;
    }
}
