package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpResponse;

import java.util.Hashtable;

public class MethodOptionsEndpoint implements IEndpoint {
    private Hashtable<String, String> allowedMethods;
    private HttpResponse httpResponse;

    public MethodOptionsEndpoint(HttpResponse httpResponse) {
        allowedMethods = new Hashtable<>();
        allowedMethods.put("GET", "200");
        allowedMethods.put("PUT", "200");
        allowedMethods.put("POST", "200");
        allowedMethods.put("HEAD", "200");
        allowedMethods.put("OPTIONS", "200");
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("method_options");
    }

    public String route(String httpMethod) {
        String statusCode = allowedMethods.get(httpMethod);

        if (statusCode == null) {
            return httpResponse.statusLine("405");
        }

        return httpResponse.statusLine("200") + httpResponse.allowHeader(allowedMethods);
    }
}
