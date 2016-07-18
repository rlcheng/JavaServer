package com.richardcheng.javaserver;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
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
    public void testServerRead_GET_Request() {
        String expectedRequest = "GET / HTTP/1.1";
        mockServer subject = new mockServer();

        String actualRequest = subject.read();

        Assert.assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void testServerWrite_200_Response() {
        String expectedResponse = "HTTP/1.1 200 OK";

        mockServer subject = new mockServer();

        subject.write();

        Assert.assertEquals(expectedResponse, subject.response);
    }

    private class mockServer extends Server{
        private Socket connectionSocket;
        public String response;

        protected void acceptConnection() {
            connectionSocket = new Socket();
        }

        protected String getRequest() {
            return "GET / HTTP/1.1";
        }

        protected void sendResponse(String message) {
            response = message;
        }
    }
}
