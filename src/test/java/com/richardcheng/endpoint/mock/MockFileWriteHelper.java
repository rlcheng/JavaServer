package com.richardcheng.endpoint.mock;

import com.richardcheng.FileHelper.FileWriteHelper;

public class MockFileWriteHelper extends FileWriteHelper {
    public MockFileWriteHelper() {

    }

    public void init(String fullPath) {

    }

    public boolean write(String message) {
        return true;
    }
}
