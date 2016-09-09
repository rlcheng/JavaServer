package com.richardcheng.endpoint.mock;

import com.richardcheng.FileHelper.FileReadHelper;

public class MockFileReadHelper extends FileReadHelper {
    public MockFileReadHelper() {

    }

    public void init(String fullPath) {

    }

    public void parseRange(String range) {

    }

    public byte[] readBytes() {
        return "hi".getBytes();
    }
}
