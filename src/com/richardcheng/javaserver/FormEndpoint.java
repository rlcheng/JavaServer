package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/26/16.
 */
public class FormEndpoint implements IEndpoint {
    public boolean match(String endpoint) {
        if (endpoint.equals("form")) {
            return true;
        }
        return false;
    }

    public String route(String httpMethod) {
        return "HTTP/1.1 200 OK\n";
    }
}
