package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpResponse;

public class InvalidEndpoint implements IEndpoint {
    private HttpResponse httpResponse;

    public InvalidEndpoint(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return true;
    }

    public String route(String httpMethod) {
        return httpResponse.statusLine("404");
    }
}
