package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpResponse;

public class FormEndpoint implements IEndpoint {
    private HttpResponse httpResponse;

    public FormEndpoint(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("form");
    }

    public String route(String httpMethod) {
        return httpResponse.statusLine("200");
    }
}
