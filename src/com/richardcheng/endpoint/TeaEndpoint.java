package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpResponse;

public class TeaEndpoint implements IEndpoint {
    private HttpResponse httpResponse;

    public TeaEndpoint(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("tea");
    }

    public String route(String httpMethod) {
        return httpResponse.statusLine("200");
    }
}
