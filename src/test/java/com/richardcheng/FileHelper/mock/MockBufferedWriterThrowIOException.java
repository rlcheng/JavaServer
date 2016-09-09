package com.richardcheng.FileHelper.mock;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class MockBufferedWriterThrowIOException extends BufferedWriter {
    public MockBufferedWriterThrowIOException(Writer out) {
        super(out);
    }

    public void write(String s) throws IOException {
        throw new IOException();
    }
}
