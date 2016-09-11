package com.richardcheng.httpIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Base64;

public class HttpRequest {
    private String method;
    private String uri;
    private String version;
    private String endpoint;
    private String data;
    private String log = "";
    private String auth = "";
    private String range = "";
    private String parameters = "";
    private String etag = "";

    public void parseMessage(BufferedReader requestMessage) {
        try {
            parseRequest(requestMessage.readLine());

            int size = 0;

            for (String line = requestMessage.readLine(); !line.isEmpty(); line = requestMessage.readLine()) {
                String parsed[] = line.split(" ");

                if (parsed[0].equals("Content-Length:")) {
                    size = Integer.parseInt(parsed[1]);
                }

                if (parsed[0].equals("Range:")) {
                    range = parsed[1].replace("bytes=", "");;
                }

                if (parsed[0].equals("Authorization:")) {
                    auth = new String(Base64.getDecoder().decode(parsed[2]));
                }

                if (parsed[0].equals("If-Match:")) {
                    etag = parsed[1].replace("\"", "");
                }
            }

            char[] charBuffer = new char[size];
            requestMessage.read(charBuffer, 0, size);
            data = new String(charBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseRequest(String request) {
        log += request;
        String parsed[] = request.split(" ");
        method = parsed[0];
        uri = parsed[1];
        version = parsed[2];
        setEndpoint();
    }

    private void setEndpoint() {
        String path[] = uri.split("/");

        if (path.length == 0) {
            endpoint = "root";
        } else {
            endpoint = path[1];
            setParameter();
        }
    }

    private void setParameter() {
        int paramExistAt = endpoint.indexOf("?");

        if (paramExistAt != -1) {
            parameters = endpoint.substring(paramExistAt, endpoint.length());
            endpoint = endpoint.replace(parameters, "");
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getVersion() {
        return version;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getData() {
        return data;
    }

    public String getLog() {
        return log;
    }

    public String getAuth() {
        return auth;
    }

    public String getRange() {
        return range;
    }

    public String getParameters() {
        return parameters;
    }

    public String getEtag() {
        return etag;
    }

    public void resetRange() {
        range = "";
    }
}
