package com.richardcheng.javaserver;

import com.richardcheng.endpoint.RootEndpoint;
import com.richardcheng.presenter.HttpResponse;
import com.richardcheng.presenter.Presenter;

public class MockRootEndpointNoMatch extends RootEndpoint {

    public MockRootEndpointNoMatch(HttpResponse httpResponse, Presenter presenter) {
        super(httpResponse, presenter);
    }

    public boolean match(String endpoint) {
        return false;
    }
}
