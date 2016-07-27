package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/26/16.
 */
public class InvalidEndpoint implements IEndpoint {
    public boolean match(String endpoint) {
        return true;
    }

    public String route(String httpMethod) {
        return "HTTP/1.1 404 NOT FOUND\n";
    }
}
