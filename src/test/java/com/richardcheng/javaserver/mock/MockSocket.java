package com.richardcheng.javaserver.mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MockSocket extends Socket {
    public MockSocket() throws IOException {
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream("stuff\n\n".getBytes(StandardCharsets.UTF_8));
    }

    public OutputStream getOutputStream() throws IOException {
        return new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }
        };
    }
}
