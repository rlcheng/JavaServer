package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpRequest;

public interface IEndpoint {
    boolean match(String endpoint);
    byte[] route(HttpRequest request);
}
