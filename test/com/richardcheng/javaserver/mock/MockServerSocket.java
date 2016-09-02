package com.richardcheng.javaserver.mock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class MockServerSocket extends ServerSocket {
    public boolean setReuseAddressCalled;
    public boolean bindCalled;

    public MockServerSocket() throws IOException {

    }

    public void setReuseAddress(boolean reuse) {
        setReuseAddressCalled = true;
    }

    public void bind(SocketAddress endpoint) throws IOException {
        bindCalled = true;
    }

    public Socket accept() throws IOException {
        MockSocket mockSocket;
        try {
            mockSocket = new MockSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mockSocket;
    }
}
