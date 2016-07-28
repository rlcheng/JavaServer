package com.richardcheng.javaserver;

public interface IEndpoint {
    boolean match(String endpoint);
    String route(String httpMethod);
}
