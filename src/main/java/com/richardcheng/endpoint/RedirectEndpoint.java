package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpResponse;
import com.richardcheng.httpIO.HttpRequest;

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

    public byte[] route(HttpRequest httpRequest) {
        return (httpResponse.statusLine("302") + httpResponse.location(port)).getBytes();
    }
}
