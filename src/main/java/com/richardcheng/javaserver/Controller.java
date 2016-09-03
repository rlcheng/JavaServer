package com.richardcheng.javaserver;

import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.endpoint.IEndpoint;

public class Controller {
    private IEndpoint[] endpoints;

    public Controller(IEndpoint[] endpoints) {
        this.endpoints = endpoints;
    }

    public byte[] routeRequest(HttpRequest request) {
        String requestEndpoint = request.getEndpoint();

        for(IEndpoint endpoint : endpoints) {
            if (endpoint.match(requestEndpoint)) {
                return endpoint.route(request);
            }
        }

        return null;
    }
}
