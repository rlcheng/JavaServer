package com.richardcheng.FileHelper.mock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MockFileInputStreamThrowIOException extends MockFileInputStream {
    public MockFileInputStreamThrowIOException(File file) throws FileNotFoundException{
        super(file);
    }

    public long skip(long n) throws IOException {
        throw new IOException();
    }
}
