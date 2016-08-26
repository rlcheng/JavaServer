package com.richardcheng.javaserver;

import java.io.File;
import java.util.LinkedHashMap;

public class FileHelper {
    private File[] files;

    public FileHelper(File[] files) {
        this.files = files;
    }

    public LinkedHashMap<String, Object> listMap() {
        LinkedHashMap<String, Object> list = new LinkedHashMap<>();

        for( File file : files) {
            list.put(file.getName(), 1);
        }

        return list;
    }
}
