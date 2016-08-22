package com.richardcheng.endpoint;

public interface IEndpoint {
    boolean match(String endpoint);
    String route(String httpMethod, String data);
}
