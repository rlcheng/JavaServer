package com.richardcheng.endpoint.mock;

import com.richardcheng.httpIO.HttpResponse;

public class MockHttpResponseRoot extends HttpResponse {
    public String statusLine(String code) {
        if (code.equals("200")) {
            return "HTTP/1.1 200 OK\r\n";
        }

        return "HTTP/1.1 405 Method Not Allowed\r\n";
    }

    public String completeResponse(String code, String message) {
        String complete = "HTTP/1.1 200 OK\r\n";
        complete += "Content-Type: text/html\r\nContent-Length: 389\r\n";
        complete += "<!DOCTYPE html>\n";
        complete += "<html>\n";
        complete += "<body>\n";
        complete += "<a href=\"/file1\">file1</a>\r\n";
        complete += "<a href=\"/file2\">file2</a>\r\n";
        complete += "<a href=\"/image.gif\">image.gif</a>\n";
        complete += "<a href=\"/image.jpeg\">image.jpeg</a>\n";
        complete += "<a href=\"/image.png\">image.png</a>\n";
        complete += "<a href=\"/partial_content.txt\">partial_content.txt</a>\n";
        complete += "<a href=\"/patch-content.txt\">patch-content.txt</a>\n";
        complete += "<a href=\"/text-file.txt\">text-file.txt</a>\n";
        complete += "</body>\n";
        complete += "</html>\n";
        return complete;
    }
}
