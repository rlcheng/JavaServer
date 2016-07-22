package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/22/16.
 */
public class HttpRequest {
    private String method;
    private String uri;
    private String version;
    private String endpoint;

    public void parseRequest(String request) {
        String parsed[] = request.split(" ");
        method = parsed[0];
        uri = parsed[1];
        version = parsed[2];
        endpoint();
    }

    private void endpoint() {
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

    public String getEndpoint() { return endpoint; }
}