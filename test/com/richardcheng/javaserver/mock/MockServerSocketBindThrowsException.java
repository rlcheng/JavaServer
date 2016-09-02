package com.richardcheng.javaserver.mock;

import java.io.IOException;
import java.net.SocketAddress;

public class MockServerSocketBindThrowsException extends MockServerSocket {
    public MockServerSocketBindThrowsException() throws IOException {
    }

    public void bind(SocketAddress endpoint) throws IOException {
        throw new IOException();
    }
}
