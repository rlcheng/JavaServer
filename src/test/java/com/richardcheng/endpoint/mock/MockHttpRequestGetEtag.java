package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpRequest;

public class MockHttpRequestGetEtag extends HttpRequest {
    public String getMethod() {
        return "GET";
    }

    public String getEtag() {
        return "c22b5f9178342609428d6f51b2c5af4c0bde6a42";
    }
}
