package com.richardcheng.javaserver;

public class FormEndpoint implements IEndpoint {
    public boolean match(String endpoint) {
        if (endpoint.equals("form")) {
            return true;
        }
        return false;
    }

    public String route(String httpMethod) {
        return "HTTP/1.1 200 OK\n";
    }
}
