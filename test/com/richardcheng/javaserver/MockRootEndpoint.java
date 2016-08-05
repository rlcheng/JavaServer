package com.richardcheng.javaserver;

import com.richardcheng.endpoint.RootEndpoint;

public class MockRootEndpoint extends RootEndpoint {

    public MockRootEndpoint(String directoryPath) {
        super(directoryPath);
    }

    public boolean match(String endpoint) {
        return true;
    }

    public String route(String httpMethod) {
        return "OK";
    }
}