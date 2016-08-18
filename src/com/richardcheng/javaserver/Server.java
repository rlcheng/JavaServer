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
        ServerArgumentHelper serverArgs = new ServerArgumentHelper(args);
        serverArgs.parseArgs();

        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>();

        if (serverArgs.path().length() > 0) {
            directoryList = new FileHandler(serverArgs.path()).listFiles();
        }

        IEndpoint[] endpoints = {
            new RootEndpoint(new HttpResponse(),
            new Presenter(directoryList)),
            new FormEndpoint(new HttpResponse()),
            new CoffeeEndpoint(new HttpResponse()),
            new TeaEndpoint(new HttpResponse()),
            new MethodOptionsEndpoint(new HttpResponse()),
            new MethodOptions2Endpoint(new HttpResponse()),
            new RedirectEndpoint(new HttpResponse(), serverArgs.port()),
            new InvalidEndpoint(new HttpResponse()) };

        Controller controller = new Controller(endpoints);
        HttpRequest request = new HttpRequest();
        Server server = new Server(new ShouldLoop(),controller, request);

        server.run(serverArgs.port());
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
