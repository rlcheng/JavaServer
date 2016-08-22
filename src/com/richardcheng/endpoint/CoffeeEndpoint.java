package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpResponse;

public class CoffeeEndpoint implements IEndpoint {
    private HttpResponse httpResponse;

    public CoffeeEndpoint(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("coffee");
    }
    public String route(String httpMethod, String data) {
        return httpResponse.completeResponse("418", "I'm a teapot");
    }
}
