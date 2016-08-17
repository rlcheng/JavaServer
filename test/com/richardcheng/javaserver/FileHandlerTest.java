package com.richardcheng.javaserver;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

public class FileHandlerTest {
    @Test
    public void listFiles_Returns_HashMapList_ofFiles_InDirectory() {
        String path = ".";
        MockFileHandler subject = new MockFileHandler(path);

        LinkedHashMap<String, Object> actualResult = subject.listFiles();

        Assert.assertTrue(actualResult.containsKey("file1"));
        Assert.assertTrue(actualResult.containsKey("file2"));
        Assert.assertTrue(actualResult.containsKey("file3"));
    }
}