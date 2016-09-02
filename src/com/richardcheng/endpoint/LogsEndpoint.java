package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.httpIO.HttpResponse;

public class LogsEndpoint implements IEndpoint {
    private HttpResponse httpResponse;

    public LogsEndpoint(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("logs");
    }

    public byte[] route(HttpRequest httpRequest) {
        if (httpRequest.getAuth().equals("admin:hunter2")) {
            return httpResponse.completeResponse("200", httpRequest.getLog()).getBytes();
        }

        return (httpResponse.statusLine("401") + httpResponse.authHeader()).getBytes();
    }
}
