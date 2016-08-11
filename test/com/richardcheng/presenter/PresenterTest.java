package com.richardcheng.presenter;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

public class PresenterTest {
    @Test
    public void view_returnsNull_whenHashMap_Null() {
        Presenter subject = new Presenter();

        String actualResult = subject.view();

        Assert.assertNull(actualResult);

        subject = new Presenter(null);

        actualResult = subject.view();

        Assert.assertNull(actualResult);
    }

    @Test
    public void view_returnsViewPage() {
        LinkedHashMap<String, Object> list = new LinkedHashMap<>();
        list.put("file1", 1);
        list.put("file2", 2);
        Presenter subject = new Presenter(list);

        String actualResult = subject.view();

        Assert.assertTrue(!actualResult.isEmpty());
        Assert.assertTrue(actualResult.contains("<a href=\"/file1\">file1</a>\n"));
        Assert.assertTrue(actualResult.contains("<a href=\"/file2\">file2</a>\n"));
    }
}