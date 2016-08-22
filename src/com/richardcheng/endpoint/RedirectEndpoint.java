package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpResponse;

public class RedirectEndpoint implements IEndpoint {
    private HttpResponse httpResponse;
    private int port;

    public RedirectEndpoint(HttpResponse httpResponse, int port) {
        this.httpResponse = httpResponse;
        this.port = port;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("redirect");
    }

    public String route(String httpMethod, String data) {
        return httpResponse.statusLine("302") + httpResponse.location(port);
    }
}
