package com.richardcheng.javaserver.mock;

import java.io.IOException;
import java.net.Socket;

public class MockServerSocketAcceptThrowsException extends MockServerSocket {
    public MockServerSocketAcceptThrowsException() throws IOException {
    }

    public Socket accept() throws IOException {
        throw new IOException();
    }
}
