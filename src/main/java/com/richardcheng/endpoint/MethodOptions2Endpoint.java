package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpResponse;
import com.richardcheng.httpIO.HttpRequest;

import java.util.Hashtable;

public class MethodOptions2Endpoint implements IEndpoint {
    private Hashtable<String, String> allowedMethods;
    private HttpResponse httpResponse;

    public MethodOptions2Endpoint(HttpResponse httpResponse) {
        allowedMethods = new Hashtable<>();
        allowedMethods.put("GET", "200");
        allowedMethods.put("OPTIONS", "200");
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("method_options2");
    }

    public byte[] route(HttpRequest httpRequest) {
        String statusCode = allowedMethods.get(httpRequest.getMethod());

        if (statusCode == null) {
            return httpResponse.statusLine("405").getBytes();
        }

        return (httpResponse.statusLine("200") + httpResponse.allowHeader(allowedMethods)).getBytes();
    }
}
