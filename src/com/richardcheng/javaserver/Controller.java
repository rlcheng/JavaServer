package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/19/16.
 */
public class Controller implements IController {
    public String routeRequest(HttpRequest request) {
        String response;

        String endpoint = request.getEndpoint();

        if (endpoint.equals("root")) {
            response = root(request.getMethod());
        }
        else if (endpoint.equals("form")) {
            response = form(request.getMethod());
        }
        else {
            response = "HTTP/1.1 404 NOT FOUND\n";
        }

        return response;
    }

    private String root(String method) {
        return "HTTP/1.1 200 OK\n";
    }

    private String form(String method) {
        return "HTTP/1.1 200 OK\n";
    }
}
