package com.richardcheng.endpoint;

public class RootEndpoint implements IEndpoint {
    public boolean match(String endpoint) {
        return endpoint.equals("root");
    }

    public String route(String httpMethod) {
        return "HTTP/1.1 200 OK\n";
    }
}
