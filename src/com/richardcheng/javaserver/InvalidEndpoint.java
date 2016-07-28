package com.richardcheng.javaserver;

public class InvalidEndpoint implements IEndpoint {
    public boolean match(String endpoint) {
        return true;
    }

    public String route(String httpMethod) {
        return "HTTP/1.1 404 NOT FOUND\n";
    }
}
