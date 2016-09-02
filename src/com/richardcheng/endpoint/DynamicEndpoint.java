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
    private String fileType;
    private boolean isText;

    public DynamicEndpoint (HttpResponse httpResponse, LinkedHashMap<String, Object> directoryList, String path) {
        this.httpResponse = httpResponse;
        this.directoryList = directoryList;
        this.path = path;

        imageAllowedMethods = new Hashtable<>();
        imageAllowedMethods.put("GET", "200");

        textAllowedMethods = new Hashtable<>();
        textAllowedMethods.put("GET", "200");
        textAllowedMethods.put("PATCH", "204");

        endpointType = new Hashtable<>();
        endpointType.put("file", textAllowedMethods);
        endpointType.put("gif", imageAllowedMethods);
        endpointType.put("jpeg", imageAllowedMethods);
        endpointType.put("png", imageAllowedMethods);
        endpointType.put("txt", textAllowedMethods);
    }

    public boolean match(String endpoint) {
        if (directoryList.containsKey(endpoint)) {
            fileType = getFileExtension(endpoint);
            allowedMethods = endpointType.get(fileType);
            fileName = endpoint;
            if (fileType.equals("file") || fileType.equals("txt")) {
                isText = true;
            } else {
                isText = false;
            }

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
        String encodedMessage = "";

        if (statusCode == null) {
            return httpResponse.statusLine("405");
        }

        FileReadHelper fileReadHelper = new FileReadHelper(path + fileName);
        if (httpRequest.getRange().length() > 0) {
            statusCode = "206";
            fileReadHelper.parseRange(httpRequest.getRange());
            httpRequest.resetRange();
        }
        byte[] contentBytes = fileReadHelper.readBytes();

        if (isText) {
            message = new String(contentBytes);
        } else {
            return httpResponse.imageHeaderResponse(statusCode, fileType, contentBytes.length);
        }

        if (httpRequest.getEtag().length() > 0) {
            encodedMessage = new SHAEncoder().encode(message, "SHA1");

            if (encodedMessage.equals(httpRequest.getEtag())) {
                new FileWriteHelper(path + fileName).write(httpRequest.getData());
                return httpResponse.statusLine(statusCode);
            }
        }

        return httpResponse.completeResponse(statusCode, message);
    }
}
