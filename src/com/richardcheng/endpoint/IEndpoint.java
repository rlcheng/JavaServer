package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpRequest;

public interface IEndpoint {
    boolean match(String endpoint);
    String route(HttpRequest request);
}
