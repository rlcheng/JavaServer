package com.richardcheng.javaserver;

import java.util.Hashtable;

public class HttpResponse {
    private static String SP = " ";
    private static String CRLF = "\r\n";
    private static final Hashtable<String, String> reasonPhrases;
    static
    {
        reasonPhrases = new Hashtable<>();
        reasonPhrases.put("200", "OK");
        reasonPhrases.put("404", "Not Found");
        reasonPhrases.put("405", "Method Not Allowed");
    }

    public String statusLine(String code) {
        return httpVersion() + SP + code + SP + reasonPhrases.get(code) + CRLF;
    }

    private String httpVersion() {
        return "HTTP/1.1";
    }

    public String entityHeader(int bodyLength) {
        return contentType() + contentLength(bodyLength);
    }

    private String contentType() {
        return "Content-Type:" + SP + "text/html" + CRLF;
    }

    private String contentLength(int bodyLength) {
        return "Content-Length:" + SP + Integer.toString(bodyLength) + CRLF;
    }

    public String completeResponse(String code, String message) {
        return statusLine(code) + entityHeader(message.length()) + CRLF + message;
    }
}
