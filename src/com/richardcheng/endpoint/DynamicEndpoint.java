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
        String encodedMessage = "";

        if (statusCode == null) {
            return httpResponse.statusLine("405");
        }

        FileReadHelper fileReadHelper = new FileReadHelper(path + fileName);
        if (httpRequest.getRange().length() > 0) {
            statusCode = "206";
            fileReadHelper.parseRange(httpRequest.getRange());
            httpRequest.resetRange(); //TODO: Rethink this please. why are we using the same instance and end up having this issue???
        }
        message = fileReadHelper.read();

        if (httpRequest.getEtag().length() > 0) {
            encodedMessage = new SHA1Encoder().encode(message);

            if (encodedMessage.equals(httpRequest.getEtag())) {
                new FileWriteHelper(path + fileName).write(httpRequest.getData());
                return httpResponse.statusLine(statusCode);
            }
        }

        return httpResponse.completeResponse(statusCode, message);
    }
}
