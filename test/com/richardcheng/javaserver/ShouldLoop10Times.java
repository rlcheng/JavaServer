package com.richardcheng.javaserver;

public class ShouldLoop10Times extends ShouldLoop {
    private static int maxLoop = 10;
    private int loopCount = 0;

    public boolean shouldLoop() {
        loopCount++;

        if (loopCount < maxLoop) {
            return true;
        }

        return false;
    }
}
