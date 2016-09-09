package com.richardcheng.FileHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteHelper {
    private File file;

    public FileWriteHelper(String path) {
        this.file = new File(path);
    }

    public void write(String message) {
        try{
            FileWriter fstream = new FileWriter(file, false);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(message);
            out.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
