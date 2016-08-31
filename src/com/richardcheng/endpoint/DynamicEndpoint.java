package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.httpIO.HttpResponse;

import java.util.Hashtable;
import java.util.LinkedHashMap;

public class DynamicEndpoint implements IEndpoint {
    private HttpResponse httpResponse;
    private LinkedHashMap<String, Object> directoryList;
    private String path;
    private String fileName;
    private Hashtable<String, String> allowedMethods;
    private Hashtable<String, String> imageAllowedMethods;
    private Hashtable<String, String> textAllowedMethods;
    private Hashtable<String, Hashtable<String, String>> endpointType;

    public DynamicEndpoint (HttpResponse httpResponse, LinkedHashMap<String, Object> directoryList, String path) {
        this.httpResponse = httpResponse;
        this.directoryList = directoryList;
        this.path = path;

        imageAllowedMethods = new Hashtable<>();
        imageAllowedMethods.put("GET", "200");

        textAllowedMethods = new Hashtable<>();
        textAllowedMethods.put("GET", "200");

        endpointType = new Hashtable<>();
        endpointType.put("file", textAllowedMethods);
        endpointType.put("gif", imageAllowedMethods);
        endpointType.put("jpeg", imageAllowedMethods);
        endpointType.put("png", imageAllowedMethods);
        endpointType.put("txt", textAllowedMethods);
    }

    public boolean match(String endpoint) {
        if (directoryList.containsKey(endpoint)) {
            allowedMethods = endpointType.get(getFileExtension(endpoint));
            fileName = endpoint;
            return true;
        }

        return false;
    }

    private String getFileExtension(String endpoint) {
        String[] nameSplit = endpoint.split("\\.");
        if (nameSplit.length == 1) {
            return "file";
        }
        return nameSplit[1];
    }

    public String route(HttpRequest httpRequest) {
        String httpMethod = httpRequest.getMethod();
        String statusCode = allowedMethods.get(httpMethod);
        String message = "";

        if (statusCode == null) {
            return httpResponse.statusLine("405");
        }

        if (httpRequest.getRange().length() > 0) {
            statusCode = "206";
            FileReadHelper fileReadHelper = new FileReadHelper(path + fileName);
            fileReadHelper.parseRange(httpRequest.getRange());
            message = fileReadHelper.read();
        } else {
            FileReadHelper fileReadHelper = new FileReadHelper(path + fileName);
            message = fileReadHelper.read();
        }

        return httpResponse.completeResponse(statusCode, message);
    }
}
