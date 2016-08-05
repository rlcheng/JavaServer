package com.richardcheng.endpoint;

public class FormEndpoint implements IEndpoint {
    public boolean match(String endpoint) {
        return endpoint.equals("form");
    }

    public String route(String httpMethod) {
        return "HTTP/1.1 200 OK\r\n";
    }
}
