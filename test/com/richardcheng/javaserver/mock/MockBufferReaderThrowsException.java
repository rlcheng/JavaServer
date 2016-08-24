package com.richardcheng.javaserver.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class MockBufferReaderThrowsException extends BufferedReader {
    public MockBufferReaderThrowsException(Reader reader) {
        super(reader);
    }

    public String readLine() throws IOException {
        throw new IOException();
    }

}
