package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.httpIO.HttpResponse;

public class TeaEndpoint implements IEndpoint {
    private HttpResponse httpResponse;

    public TeaEndpoint(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("tea");
    }

    public byte[] route(HttpRequest httpRequest) {
        return httpResponse.statusLine("200").getBytes();
    }
}
