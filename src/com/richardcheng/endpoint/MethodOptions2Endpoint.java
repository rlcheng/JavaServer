package com.richardcheng.endpoint;

import com.richardcheng.presenter.HttpResponse;

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

    public String route(String httpMethod) {
        String statusCode = allowedMethods.get(httpMethod);

        if (statusCode == null) {
            return httpResponse.statusLine("405");
        }

        return httpResponse.statusLine("200") + httpResponse.allowHeader(allowedMethods);
    }
}
