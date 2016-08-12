package com.richardcheng.javaserver;

import java.io.File;
import java.util.LinkedHashMap;

public class FileHandler {
    private File directory;

    public FileHandler(String path) {
        this.directory = new File(path);
    }

    public LinkedHashMap<String, Object> listFiles() {
        File[] files = directoryList();
        LinkedHashMap<String, Object> list = new LinkedHashMap<>();

        for( File file : files) {
            list.put(file.getName(), 1);
        }

        return list;
    }

    protected File[] directoryList() {
        return directory.listFiles();
    }
}
