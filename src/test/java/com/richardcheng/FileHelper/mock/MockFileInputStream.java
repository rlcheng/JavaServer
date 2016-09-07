package com.richardcheng.FileHelper.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MockFileInputStream extends FileInputStream {
    public MockFileInputStream(File file) throws FileNotFoundException {
        super(file);
    }

    public long skip(long n) throws IOException {
        return 0;
    }

    public int read(byte[] bytes) throws IOException {
        byte[] tempBytes = "hi".getBytes();
        bytes[0] = tempBytes[0];
        bytes[1] = tempBytes[1];

        return 2;
    }


}
