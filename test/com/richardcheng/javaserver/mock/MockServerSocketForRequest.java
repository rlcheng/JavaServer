package com.richardcheng.javaserver.mock;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

public class MockServerSocketForRequest extends MockServerSocket {
    public boolean setReuseAddressCalled;
    public boolean bindCalled;

    public MockServerSocketForRequest() throws IOException {
    }

    public Socket accept() throws IOException {
        MockSocketGetInputStreamThrowsIOException mockSocket;
        try {
            mockSocket = new MockSocketGetInputStreamThrowsIOException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mockSocket;
    }
}
