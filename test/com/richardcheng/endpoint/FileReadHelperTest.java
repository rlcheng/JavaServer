package com.richardcheng.endpoint;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class FileReadHelperTest {
    @Ignore
    public void readRange_returnsRangeRead() {
        String path = "/Users/richardcheng/Documents/cob_spec/public/partial_content.txt";
        FileReadHelper subject = new FileReadHelper(path);

        subject.parseRange("0-4");
        String actual = subject.read();

        Assert.assertEquals(actual, "This ");
    }
}