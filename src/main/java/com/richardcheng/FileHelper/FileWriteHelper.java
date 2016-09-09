package com.richardcheng.FileHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteHelper {
    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public FileWriteHelper() {
    }

    public void init(String fullPath) {
        file = new File(fullPath);
        try {
            fileWriter = new FileWriter(file, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bufferedWriter = new BufferedWriter(fileWriter);
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public boolean write(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
