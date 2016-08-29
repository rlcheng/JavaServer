package com.richardcheng.endpoint;

import org.junit.Assert;
import org.junit.Test;

public class PartialReadFileHelperTest {
    @Test
    public void readRange_returnsRangeRead() {
        String path = "/Users/richardcheng/Documents/cob_spec/public/partial_content.txt";
        PartialReadFileHelper subject = new PartialReadFileHelper(path);

        subject.parseRange("0-4");
        String actual = subject.read();

        Assert.assertEquals(actual, "This ");
    }
}