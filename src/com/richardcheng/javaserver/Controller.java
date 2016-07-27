package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/19/16.
 */
public class Controller implements IController {
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
