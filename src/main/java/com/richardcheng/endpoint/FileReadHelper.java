package com.richardcheng.endpoint;

import java.io.*;

public class FileReadHelper {
    private File file;
    private int fileSize;
    private int start = 0;
    private int stop = 0;
    private int size;

    public FileReadHelper(String path) {
        this.file = new File(path);
        fileSize = (int)(long)file.length();
        size = fileSize;
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

    public String read() {
        BufferedReader bufferedReader;

        char[] charBuffer = new char[size];

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            bufferedReader.skip(start);
            bufferedReader.read(charBuffer, 0, size);

            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new String(charBuffer);
    }

    public byte[] readBytes() {
        FileInputStream fileInputStream;
        byte[] content = new byte[size];

        try {
            fileInputStream = new FileInputStream(file);

            fileInputStream.skip(start);
            fileInputStream.read(content);

            fileInputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }
}
