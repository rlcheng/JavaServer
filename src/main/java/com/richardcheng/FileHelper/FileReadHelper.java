package com.richardcheng.FileHelper;

import java.io.*;

public class FileReadHelper {
    private File file;
    private int fileSize;
    private int start = 0;
    private int stop = 0;
    private int size;

    public FileReadHelper(File file) {
        this.file = file;
        fileSize = (int)(long)file.length();
        size = fileSize;
    }

    public int getFileSize() {
        return fileSize;
    }

    public int getStart() {
        return start;
    }

    public int getStop() {
        return stop;
    }

    public int getSize() {
        return size;
    }

    public void parseRange(String range) {
        String[] parsedRange = range.split("-");

        if (range.endsWith("-")) {
            start = Integer.parseInt(parsedRange[0]);
            stop = fileSize - 1;
        } else if (range.startsWith("-")) {
            start = fileSize - Integer.parseInt(parsedRange[1]);
            stop = fileSize - 1;
        } else if (range.contains("-")){
            start = Integer.parseInt(parsedRange[0]);
            stop = Integer.parseInt(parsedRange[1]);
        } else {
            start = Integer.parseInt(parsedRange[0]);
            stop = fileSize - 1;
        }
        size = stop - start + 1;
    }

    public byte[] readBytes(FileInputStream fileInputStream) {
        byte[] content = new byte[size];

        try {
            fileInputStream.skip(start);
            fileInputStream.read(content);

            fileInputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }
}
