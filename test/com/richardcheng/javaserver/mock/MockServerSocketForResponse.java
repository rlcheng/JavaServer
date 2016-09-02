package com.richardcheng.javaserver.mock;

import java.io.IOException;
import java.net.Socket;

public class MockServerSocketForResponse extends MockServerSocket{
    public boolean setReuseAddressCalled;
    public boolean bindCalled;

    public MockServerSocketForResponse() throws IOException {
    }

    public Socket accept() throws IOException {
        MockSocketGetOutputStreamThrowsIOException mockSocket;
        try {
            mockSocket = new MockSocketGetOutputStreamThrowsIOException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mockSocket;
    }
}
