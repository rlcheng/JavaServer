package com.richardcheng.FileHelper;

import java.io.*;

public class FileReadHelper {
    private File file;
    private int fileSize;
    private int start = 0;
    private int stop = 0;
    private int readSize;
    private FileInputStream fileInputStream;

    public FileReadHelper() {
    }

    public void init(String fullPath) {
        file = new File(fullPath);
        fileSize = (int)(long)file.length();
        readSize = fileSize;

        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
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
        readSize = stop - start + 1;
    }

    public byte[] readBytes() {
        byte[] content = new byte[readSize];

        try {
            fileInputStream.skip(start);
            fileInputStream.read(content);

            fileInputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public void setReadSize(int readSize) {
        this.readSize = readSize;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public int getStart() {
        return start;
    }

    public int getStop() {
        return stop;
    }

    public int getReadSize() {
        return readSize;
    }

    public int getFileSize() {
        return fileSize;
    }
}
