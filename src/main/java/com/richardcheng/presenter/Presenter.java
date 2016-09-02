package com.richardcheng.presenter;

import java.util.LinkedHashMap;

public class Presenter {
    private LinkedHashMap<String, Object> directoryList;

    public Presenter() {
        directoryList = null;
    }

    public Presenter(LinkedHashMap<String, Object> directoryList) {
        this.directoryList = directoryList;
    }

    public String view() {
        if (directoryList == null) {
            return null;
        }

        String htmlPage = "<!DOCTYPE html>\n";
        htmlPage += "<html>\n";
        htmlPage += "<body>\n";

        for(String fileName : directoryList.keySet()) {
            htmlPage += "<a href=\"/"+fileName+"\">"+fileName+"</a>\n";
            htmlPage += "<br>\n";
        }

        htmlPage += "</body>\n";
        htmlPage += "</html>\n";

        return htmlPage;
    }
}
