package com.richardcheng.javaserver.mock;

import java.io.IOException;

public class MockServerSocketForStop extends MockServerSocket {
    public MockServerSocketForStop() throws IOException {
    }

    public void close() throws IOException {
        throw new IOException();
    }
}
