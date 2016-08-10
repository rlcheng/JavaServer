package com.richardcheng.javaserver;

import org.junit.Assert;
import org.junit.Test;

public class ServerTest {
    @Test
    public void testServerRun() {
        MockController controller = new MockController(null);
        MockHttpRequest httpRequest = new MockHttpRequest();
        MockServer subject = new MockServer(controller, httpRequest);
        int port = 5000;

        subject.run(port);

        Assert.assertTrue(subject.createCalled);
        Assert.assertEquals(10, subject.iterator);
        Assert.assertTrue(subject.acceptCalled);
        Assert.assertTrue(subject.requestCalled);
        Assert.assertTrue(subject.responseCalled);
    }

    @Test
    public void testServerStop() {
        MockController controller = new MockController(null);
        MockHttpRequest httpRequest = new MockHttpRequest();
        MockServer subject = new MockServer(controller, httpRequest);

        subject.stop();

        Assert.assertTrue(subject.stopCalled);
    }
}
