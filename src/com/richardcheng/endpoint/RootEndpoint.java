package com.richardcheng.endpoint;

import com.richardcheng.javaserver.HttpResponse;

import java.util.Hashtable;

public class RootEndpoint implements IEndpoint {
    private Hashtable<String, String> allowedMethods;
    private String directoryPath;
    private HttpResponse httpResponse;
    private String responseBody;

    public RootEndpoint(String directoryPath) {
        allowedMethods = new Hashtable<>();
        allowedMethods.put("GET", "200");
        this.directoryPath = directoryPath;
        responseBody = "";
    }

    public boolean match(String endpoint) {
        return endpoint.equals("root");
    }

    public String route(String httpMethod) {
        String statusCode = allowedMethods.get(httpMethod);

        httpResponse = new HttpResponse();

        if (statusCode == null) {
            return httpResponse.statusLine("405");
        }

        responseBody += "<!DOCTYPE html>\n";
        responseBody += "<html>\n";
        responseBody += "<body>\n";
        responseBody += "<a href=\"/file1\">file1</a>\r\n";
        responseBody += "<a href=\"/file2\">file2</a>\r\n";
        responseBody += "<a href=\"/image.gif\">image.gif</a>\n";
        responseBody += "<a href=\"/image.jpeg\">image.jpeg</a>\n";
        responseBody += "<a href=\"/image.png\">image.png</a>\n";
        responseBody += "<a href=\"/partial_content.txt\">partial_content.txt</a>\n";
        responseBody += "<a href=\"/patch-content.txt\">patch-content.txt</a>\n";
        responseBody += "<a href=\"/text-file.txt\">text-file.txt</a>\n";
        responseBody += "</body>\n";
        responseBody += "</html>\n";

        return httpResponse.completeResponse(statusCode, responseBody);
    }
}
