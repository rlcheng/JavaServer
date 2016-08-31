package com.richardcheng.endpoint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReadHelper {
    private File file;
    private int fileSize;
    public int start = 0;
    public int stop = 0;
    public int size;

    //TO-DO move file out of here so you can do Dependency Injection!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public FileReadHelper(String path){
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
}
