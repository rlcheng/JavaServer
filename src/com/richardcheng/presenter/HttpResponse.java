package com.richardcheng.presenter;

import java.util.Hashtable;

public class HttpResponse {
    private static String SP = " ";
    private static String CRLF = "\r\n";
    private static String SEP = ",";
    private static final Hashtable<String, String> reasonPhrases;
    static
    {
        reasonPhrases = new Hashtable<>();
        reasonPhrases.put("200", "OK");
        reasonPhrases.put("302", "Found");
        reasonPhrases.put("404", "Not Found");
        reasonPhrases.put("405", "Method Not Allowed");
        reasonPhrases.put("418", "I'm a teapot");
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

    public String allowHeader(Hashtable<String, String> allowedMethods) {
        String allowed = "Allow: ";

        for (String method : allowedMethods.keySet()) {
            allowed += method + SEP;
        }

        return allowed.replaceAll(",$", "") + CRLF;
    }

    public String location(int port) {
        return "Location: http://localhost:" + Integer.toString(port) + "/" + CRLF;
    }

    public String completeResponse(String code, String message) {
        return statusLine(code) + entityHeader(message.length()) + CRLF + message;
    }
}
