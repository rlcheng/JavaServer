package com.richardcheng.httpIO;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private String method;
    private String uri;
    private String version;
    private String endpoint;
    private String data;

    public void parseMessage(BufferedReader requestMessage) {
        try {
            parseRequest(requestMessage.readLine());

            int size = 0;

            for (String line = requestMessage.readLine(); !line.isEmpty(); line = requestMessage.readLine()) {
                String parsed[] = line.split(" ");

                if (parsed[0].equals("Content-Length:")) {
                    size = Integer.parseInt(parsed[1]);
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
        }
        else {
            endpoint = path[1];
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
}
