package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.httpIO.HttpResponse;
import com.richardcheng.presenter.Presenter;

import java.util.Hashtable;

public class RootEndpoint implements IEndpoint {
    private Hashtable<String, String> allowedMethods;
    private HttpResponse httpResponse;
    private Presenter presenter;

    public RootEndpoint(HttpResponse httpResponse, Presenter presenter) {
        allowedMethods = new Hashtable<>();
        allowedMethods.put("GET", "200");
        allowedMethods.put("HEAD", "200");
        this.httpResponse = httpResponse;
        this.presenter = presenter;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("root");
    }

    public byte[] route(HttpRequest httpRequest) {
        String httpMethod = httpRequest.getMethod();
        String statusCode = allowedMethods.get(httpMethod);

        if (statusCode == null) {
            return httpResponse.statusLine("405").getBytes();
        }

        if (httpMethod.equals("HEAD")) {
            return httpResponse.statusLine(statusCode).getBytes();
        }

        return httpResponse.completeResponse(statusCode, presenter.view()).getBytes();
    }
}
