package com.richardcheng.endpoint;

import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.httpIO.HttpResponse;
import com.richardcheng.percentformat.PercentDecoder;

import java.util.Hashtable;

public class ParametersEndpoint implements IEndpoint {
    private Hashtable<String, String> allowedMethods;
    private HttpResponse httpResponse;

    public ParametersEndpoint (HttpResponse httpResponse) {
        allowedMethods = new Hashtable<>();
        allowedMethods.put("GET", "200");
        this.httpResponse = httpResponse;
    }

    public boolean match(String endpoint) {
        return endpoint.equals("parameters");
    }

    public byte[] route(HttpRequest httpRequest) {
        String httpMethod = httpRequest.getMethod();
        String statusCode = allowedMethods.get(httpMethod);

        if (statusCode == null) {
            return httpResponse.statusLine("405").getBytes();
        }

        String encodedParams = httpRequest.getParameters();

        String decodedParams = new PercentDecoder().decode(encodedParams);

        return httpResponse.completeResponse(statusCode, decodedParams + "\r\n").getBytes();
    }
}
