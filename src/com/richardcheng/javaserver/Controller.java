package com.richardcheng.javaserver;

import com.richardcheng.endpoint.*;
import com.richardcheng.httpIO.HttpRequest;

public class Controller {
    private IEndpoint[] endpoints;

    public Controller(IEndpoint[] endpoints) {
        this.endpoints = endpoints;
    }

    public String routeRequest(HttpRequest request) {
        String requestEndpoint = request.getEndpoint();

        for(IEndpoint endpoint : endpoints) {
            if (endpoint.match(requestEndpoint)) {
                return endpoint.route(request);
            }
        }

        return null;
    }
}
