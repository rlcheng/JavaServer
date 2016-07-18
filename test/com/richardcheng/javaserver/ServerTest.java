package com.richardcheng.javaserver;

import org.junit.Test;
import org.junit.Assert;

import java.net.*;
import java.io.*;

/**
 * Created by richardcheng on 7/14/16.
 */
public class ServerTest {
    @Test
    public void testServerInitWithPort() {
        int expectedPort = 5000;

        Server subject = new Server(expectedPort);

        Assert.assertEquals(expectedPort, subject.getPort());
    }

    @Test
    public void testServerStartStop() {
        int port = 5000;

        Server subject = new Server(port);
        subject.start();
        subject.stop();

        Assert.assertTrue(true);
    }

    @Test
    public void testServerRead() {
        String expected = "GET";
        mockServer subject = new mockServer();

        String request = subject.read();

        Assert.assertEquals(expected, request);
    }

    private class mockServer extends Server{
        private Socket connectionSocket;

        protected void acceptConnection() {
            connectionSocket = new Socket();
        }

        protected String getRequest() {
            return "GET";
        }
    }
}
