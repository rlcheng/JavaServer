package com.richardcheng.javaserver.mock;

import com.richardcheng.javaserver.mock.MockSocket;

import java.io.IOException;
import java.io.InputStream;

public class MockSocketGetInputStreamThrowsIOException extends MockSocket {
    public MockSocketGetInputStreamThrowsIOException() throws IOException {
    }

    public InputStream getInputStream() throws IOException {
        throw new IOException();
    }
}
