package com.richardcheng.javaserver;

import com.richardcheng.endpoint.*;
import com.richardcheng.presenter.HttpResponse;
import com.richardcheng.presenter.Presenter;

import java.net.*;
import java.io.*;
import java.util.LinkedHashMap;

public class Server {
    private Controller controller;
    private HttpRequest request;
    private Socket requestSocket;
    private ServerSocket serverSocket;
    private ShouldLoop serverRunShouldLoop;

    public static void main(String[] args) {
        int port = 5000;
        String publicDirPath;
        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>();

        if (args.length == 4) {
            port = Integer.parseInt(args[1]);
            publicDirPath = args[3];
            directoryList = new FileHandler(publicDirPath).listFiles();
        }

        IEndpoint[] endpoints = {
            new RootEndpoint(new HttpResponse(),
            new Presenter(directoryList)),
            new FormEndpoint(new HttpResponse()),
            new CoffeeEndpoint(new HttpResponse()),
            new TeaEndpoint(new HttpResponse()),
            new MethodOptionsEndpoint(new HttpResponse()),
            new MethodOptions2Endpoint(new HttpResponse()),
            new InvalidEndpoint(new HttpResponse()) };

        Controller controller = new Controller(endpoints);
        HttpRequest request = new HttpRequest();
        Server server = new Server(new ShouldLoop(),controller, request);

        server.run(port);
    }

    public Server(ShouldLoop serverRunShouldLoop, Controller controller, HttpRequest request) {
        this.controller = controller;
        this.request = request;
        this.serverRunShouldLoop = serverRunShouldLoop;
    }

    public void run(int port) {
        this.create(port);
        try {
            while (serverRunShouldLoop.shouldLoop()) {
                requestSocket = this.accept();
                this.request();
                this.response();
            }
        }
        finally {
            this.stop();
        }
    }

    protected void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void create(int port) {
        try {
            serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Socket accept() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void request() {
        String requestString;
        try {
            BufferedReader requestMessage = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
            requestString = requestMessage.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        request.parseRequest(requestString);
    }

    protected void response() {
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
