package com.richardcheng.javaserver;

import org.junit.Assert;
import org.junit.Test;

public class ServerTest {
    @Test (timeout = 1000)
    public void run_LoopsThrough_Accept_Request_Response_Methods() {
        MockController controller = new MockController(null);
        MockHttpRequest httpRequest = new MockHttpRequest();
        MockServer subject = new MockServer(new ShouldLoop10Times(), controller, httpRequest);
        int port = 5000;

        subject.run(port);

        Assert.assertTrue(subject.createCalled);
        Assert.assertTrue(subject.acceptCalled);
        Assert.assertTrue(subject.requestCalled);
        Assert.assertTrue(subject.responseCalled);
    }
}
