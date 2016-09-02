package com.richardcheng.javaserver;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.LinkedHashMap;

public class FileHelperTest {
    @Test
    public void listFiles_Returns_HashMapList_ofFiles_InDirectory() {
        File[] files = { new File("file1"), new File("file2"), new File("file3") };
        FileHelper subject = new FileHelper(files);

        LinkedHashMap<String, Object> actualResult = subject.listMap();

        Assert.assertTrue(actualResult.containsKey("file1"));
        Assert.assertTrue(actualResult.containsKey("file2"));
        Assert.assertTrue(actualResult.containsKey("file3"));
    }
}