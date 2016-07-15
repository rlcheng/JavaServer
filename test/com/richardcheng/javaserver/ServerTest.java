package com.richardcheng.javaserver;

import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

/**
 * Created by richardcheng on 7/14/16.
 */
public class ServerTest {
    @Test
    public void testServerInitWithPort() {
        int expectedPort = 5000;

        Server server = new Server(expectedPort);

        Assert.assertEquals(expectedPort, server.getPort());
    }

    @Test
    public void testServerStartStop() {
        int port = 5000;

        Server server = new Server(port);
        server.start();
        server.stop();

        Assert.assertTrue(true);
    }

    @Test
    public void testServerReadWrite() {
        Server server = new Server(5000);

        server.start();
        String request = server.read();
        server.write();
        server.stop();

        Assert.assertNotNull(request);
    }
}
