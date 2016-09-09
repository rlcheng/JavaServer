package com.richardcheng.FileHelper.mock;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class MockBufferedWriter extends BufferedWriter {
    public MockBufferedWriter(Writer out) {
        super(out);
    }

    public void write(String s) throws IOException {

    }
}
