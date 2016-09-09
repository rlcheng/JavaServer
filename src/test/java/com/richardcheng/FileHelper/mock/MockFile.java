package com.richardcheng.FileHelper.mock;

import java.io.File;

public class MockFile extends File {
    public MockFile(String pathname) throws NullPointerException{
        super(pathname);
    }

    public long length() {
        return 10;
    }
}
