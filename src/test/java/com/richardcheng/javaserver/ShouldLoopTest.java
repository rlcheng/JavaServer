package com.richardcheng.javaserver;

import org.junit.Assert;
import org.junit.Test;

public class ShouldLoopTest {
    @Test
    public void shouldLoop_returns_true() {
        ShouldLoop subject = new ShouldLoop();

        boolean actualResult = subject.shouldLoop();

        Assert.assertTrue(actualResult);
    }

}