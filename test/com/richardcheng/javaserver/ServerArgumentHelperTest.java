package com.richardcheng.javaserver;

import org.junit.Assert;
import org.junit.Test;

public class ServerArgumentHelperTest {
    @Test
    public void getArgs_SetsObject_WithDefaultPort_andNoDirectoryPath_IfArgArrayEmpty() {
        String[] args = { "" };
        ServerArgumentHelper subject = new ServerArgumentHelper(args);
        int expectedPort = 5000;
        String expectedPath = "";

        subject.getArgs();

        Assert.assertEquals(expectedPort, subject.port());
        Assert.assertEquals(expectedPath, subject.path());
    }

    @Test
    public void getArgs_SetsObject_WithSpecifiedPort_andNoDirectoryPath_IfPathNotProvided() {
        String[] args = { "-p", "3000" };
        ServerArgumentHelper subject = new ServerArgumentHelper(args);
        int expectedPort = 3000;
        String expectedPath = "";

        subject.getArgs();

        Assert.assertEquals(expectedPort, subject.port());
        Assert.assertEquals(expectedPath, subject.path());
    }

    @Test
    public void getArgs_SetsObject_WithSpecifiedPath_andDefaultPort_IfPortNotProvided() {
        String[] args = { "-d", "/path" };
        ServerArgumentHelper subject = new ServerArgumentHelper(args);
        int expectedPort = 5000;
        String expectedPath = "/path";

        subject.getArgs();

        Assert.assertEquals(expectedPort, subject.port());
        Assert.assertEquals(expectedPath, subject.path());
    }

    @Test
    public void getArgs_SetsObject_WithSpecifiedPortandPath_WherePathDoesntHavetoBeLast() {
        String[] args = { "-d", "/path", "-p", "3000" };
        ServerArgumentHelper subject = new ServerArgumentHelper(args);
        int expectedPort = 3000;
        String expectedPath = "/path";

        subject.getArgs();

        Assert.assertEquals(expectedPort, subject.port());
        Assert.assertEquals(expectedPath, subject.path());
    }

    @Test
    public void getArgs_SetsObject_WithDefaultsIgnoringSpecifiedArgs_IfStringArg_LengthIsOddNumbered() {
        String[] args = { "5000", "-d", "/path"};
        ServerArgumentHelper subject = new ServerArgumentHelper(args);
        int expectedPort = 5000;
        String expectedPath = "";

        subject.getArgs();

        Assert.assertEquals(expectedPort, subject.port());
        Assert.assertEquals(expectedPath, subject.path());
    }
}