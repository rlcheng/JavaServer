package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.httpIO.HttpResponse;

import java.util.Hashtable;

public class FormEndpoint implements IEndpoint {
    private Hashtable<String, String> allowedMethods;
    private HttpResponse httpResponse;
    private String data = "";

    public FormEndpoint(HttpResponse httpResponse) {
        allowedMethods = new Hashtable<>();
        allowedMethods.put("GET", "200");
        allowedMethods.put("PUT", "200");
        allowedMethods.put("POST", "200");
        allowedMethods.put("DELETE", "200");
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("form");
    }

    public String route(HttpRequest httpRequest) {
        String httpMethod = httpRequest.getMethod();
        String statusCode = allowedMethods.get(httpMethod);

        if (statusCode == null) {
            return httpResponse.statusLine("405");
        }

        if (canPost(httpMethod) ^ (canPut(httpMethod))) {
            this.data = httpRequest.getData();
        } else if (httpMethod.equals("DELETE")) {
            this.data = "";
        }

        return httpResponse.completeResponse(statusCode, this.data);
    }

    private boolean canPost(String httpMethod) {
        return (httpMethod.equals("POST") && data.length() == 0);
    }

    private boolean canPut(String httpMethod) {
        return (httpMethod.equals("PUT") && data.length() > 0);
    }
}
