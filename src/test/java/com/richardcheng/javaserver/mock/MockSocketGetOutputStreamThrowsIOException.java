package com.richardcheng.javaserver.mock;

import java.io.IOException;
import java.io.OutputStream;

public class MockSocketGetOutputStreamThrowsIOException extends MockSocket {
    public MockSocketGetOutputStreamThrowsIOException() throws IOException {
    }

    public OutputStream getOutputStream() throws IOException {
        return new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                throw new IOException();
            }
        };
    }
}
