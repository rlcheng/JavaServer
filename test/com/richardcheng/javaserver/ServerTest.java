package com.richardcheng.javaserver;

import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

/**
 * Created by richardcheng on 7/14/16.
 */
public class ServerTest {
    @Test
    public void testServerInitWithPort() throws IOException {
        int expectedPort = 5000;

        Server server = new Server(expectedPort);

        Assert.assertEquals(expectedPort, server.getPort());
    }

    @Test
    public void testServerStartStop() throws IOException {
        int port = 5000;

        Server server = new Server(port);
        server.start();
        server.stop();

        Assert.assertTrue(true);
    }

    @Test
    public void testServerReadWrite() throws IOException {
        Server server = new Server(5000);

        server.start();
        String reply = server.read();
        server.write();
        server.stop();

        Assert.assertNotNull(reply);
    }

    private void client() throws IOException {

    }
}
