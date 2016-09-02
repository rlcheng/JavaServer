package com.richardcheng.javaserver.mock;

import com.richardcheng.endpoint.RootEndpoint;
import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.httpIO.HttpResponse;
import com.richardcheng.presenter.Presenter;

public class MockRootEndpoint extends RootEndpoint {

    public MockRootEndpoint(HttpResponse httpResponse, Presenter presenter) {
        super(httpResponse, presenter);
    }

    public boolean match(String endpoint) {
        return true;
    }

    public String route(HttpRequest httpRequest) {
        return "OK";
    }
}
