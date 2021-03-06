package com.richardcheng.endpoint;

import com.richardcheng.FileHelper.FileReadHelper;
import com.richardcheng.FileHelper.FileWriteHelper;
import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.httpIO.HttpResponse;

import java.util.Hashtable;
import java.util.LinkedHashMap;

public class DynamicEndpoint implements IEndpoint {
    private HttpResponse httpResponse;
    private LinkedHashMap<String, Object> directoryList;
    private String path;
    private FileReadHelper fileReadHelper;
    private FileWriteHelper fileWriteHelper;
    private String fileName;
    private Hashtable<String, String> allowedMethods;
    private Hashtable<String, String> imageAllowedMethods;
    private Hashtable<String, String> textAllowedMethods;
    private Hashtable<String, Hashtable<String, String>> endpointType;
    private String fileType;
    private boolean isText;

    public DynamicEndpoint (HttpResponse httpResponse,
                            LinkedHashMap<String, Object> directoryList,
                            String path,
                            FileReadHelper fileReadHelper,
                            FileWriteHelper fileWriteHelper) {
        this.httpResponse = httpResponse;
        this.directoryList = directoryList;
        this.path = path;
        this.fileReadHelper = fileReadHelper;
        this.fileWriteHelper = fileWriteHelper;

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

    public byte[] route(HttpRequest httpRequest) {
        String httpMethod = httpRequest.getMethod();
        String statusCode = allowedMethods.get(httpMethod);
        String message;

        if (statusCode == null) {
            return httpResponse.statusLine("405").getBytes();
        }

        fileReadHelper.init(path + fileName);
        if (httpRequest.getRange().length() > 0) {
            statusCode = "206";
            fileReadHelper.parseRange(httpRequest.getRange());
            httpRequest.resetRange();
        }

        byte[] contentBytes = fileReadHelper.readBytes();

        if (isText) {
            message = new String(contentBytes);
        } else {
            byte[] imageHeader = httpResponse.imageHeaderResponse(statusCode, fileType, contentBytes.length).getBytes();
            byte[] completeResponse = new byte[imageHeader.length + contentBytes.length];
            System.arraycopy(imageHeader, 0, completeResponse, 0, imageHeader.length);
            System.arraycopy(contentBytes, 0, completeResponse, imageHeader.length, contentBytes.length);
            return completeResponse;
        }

        if (httpRequest.getEtag().length() > 0) {
            String encodedMessage = new SHAEncoder().encode(message, "SHA1");

            if (encodedMessage.equals(httpRequest.getEtag())) {
                fileWriteHelper.init(path + fileName);
                fileWriteHelper.write(httpRequest.getData());
                return httpResponse.statusLine(statusCode).getBytes();
            }
        }

        return httpResponse.completeResponse(statusCode, message).getBytes();
    }
}
