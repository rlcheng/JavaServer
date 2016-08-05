package com.richardcheng.javaserver;

import com.richardcheng.endpoint.*;
import java.net.*;
import java.io.*;

public class Server {
    private Controller controller;
    private HttpRequest request;
    private Socket requestSocket;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        int port;
        String publicDirPath = null;
        if (args.length == 2) {
            port = Integer.parseInt(args[0]);
            publicDirPath = args[1];
        }
        else {
            port = 5000;
        }

        IEndpoint[] endpoints = { new RootEndpoint(new HttpResponse()), new FormEndpoint(new HttpResponse()), new InvalidEndpoint(new HttpResponse()) };
        Controller controller = new Controller(endpoints);
        HttpRequest request = new HttpRequest();
        Server server = new Server(controller, request);

        server.run(port);
        server.stop();
    }

    public Server(Controller controller, HttpRequest request) {
        this.controller = controller;
        this.request = request;
    }

    public void run(int port) {
        this.create(port);
        try {
            while (true) {
                requestSocket = this.accept();
                this.request();
                this.response();
            }
        }
        finally {
            this.stop();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void create(int port) {
        try {
            serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Socket accept() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void request() {
        String requestString;
        try {
            BufferedReader requestMessage = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
            requestString = requestMessage.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        request.parseRequest(requestString);
    }

    private void response() {
        String response = controller.routeRequest(request);
        try {
            DataOutputStream responseStream = new DataOutputStream(requestSocket.getOutputStream());
            responseStream.writeBytes(response);
            responseStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
