package com.richardcheng.javaserver;

import java.io.File;

public class MockFileHandler extends FileHandler {
    private File directory;

    public MockFileHandler(String path) {
        super(path);
    }

    protected File[] directoryList() {
        File[] files = { new File("file1"), new File("file2"), new File("file3") };
        return files;
    }
}
