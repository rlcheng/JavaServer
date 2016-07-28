package com.richardcheng.javaserver;

import com.richardcheng.endpoint.*;

public class Controller {
    public String routeRequest(HttpRequest request) {
        String response = "";
        String requestEndpoint = request.getEndpoint();
        IEndpoint[] endpoints = { new RootEndpoint(), new FormEndpoint(), new InvalidEndpoint() };

        for(IEndpoint endpoint : endpoints) {
            if (endpoint.match(requestEndpoint)) {
                response = endpoint.route(request.getMethod());
                break;
            }
        }

        return response;
    }
}
