package com.richardcheng.javaserver;

import com.richardcheng.endpoint.*;
import com.richardcheng.httpIO.HttpRequest;
import com.richardcheng.httpIO.HttpResponse;
import com.richardcheng.presenter.Presenter;

import java.net.*;
import java.io.*;
import java.util.LinkedHashMap;

public class Server {
    private Controller controller;
    private HttpRequest request;
    private ServerSocket serverSocket;
    private ShouldLoop serverRunShouldLoop;

    public static void main(String[] args) {
        ServerArgumentHelper serverArgs = new ServerArgumentHelper(args);
        serverArgs.parseArgs();

        LinkedHashMap<String, Object> directoryList = new LinkedHashMap<>();

        if (serverArgs.path().length() > 0) {
            File[] directory = new File(serverArgs.path()).listFiles();
            directoryList = new FileHelper(directory).listMap();
        }

        IEndpoint[] endpoints = {
            new RootEndpoint(new HttpResponse(), new Presenter(directoryList)),
            new FormEndpoint(new HttpResponse()),
            new CoffeeEndpoint(new HttpResponse()),
            new TeaEndpoint(new HttpResponse()),
            new MethodOptionsEndpoint(new HttpResponse()),
            new MethodOptions2Endpoint(new HttpResponse()),
            new RedirectEndpoint(new HttpResponse(), serverArgs.port()),
            new LogsEndpoint(new HttpResponse()),
            new ParametersEndpoint(new HttpResponse()),
            new DynamicEndpoint(new HttpResponse(), directoryList, serverArgs.path()),
            new InvalidEndpoint(new HttpResponse()) };

        Controller controller = new Controller(endpoints);
        HttpRequest request = new HttpRequest();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Server server = new Server(new ShouldLoop(), serverSocket,controller, request);

        server.run(serverArgs.port());
    }

    public Server(ShouldLoop serverRunShouldLoop,ServerSocket serverSocket, Controller controller, HttpRequest request) {
        this.controller = controller;
        this.request = request;
        this.serverRunShouldLoop = serverRunShouldLoop;
        this.serverSocket = serverSocket;
    }

    public void run(int port) {
        try {
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(port));
            while (serverRunShouldLoop.shouldLoop()) {
                Socket requestSocket = serverSocket.accept();
                this.request(requestSocket);
                this.response(requestSocket);
            }
        } catch (Exception e) {
            throw new RuntimeException("something went wrong");
        } finally {
            this.stop();
        }
    }

    private void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void request(Socket requestSocket) {
        BufferedReader requestMessage;

        try {
            requestMessage = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        request.parseMessage(requestMessage);
    }

    private void response(Socket requestSocket) {
        byte[] response = controller.routeRequest(request);
        try {
            DataOutputStream responseStream = new DataOutputStream(requestSocket.getOutputStream());
            responseStream.write(response);
            responseStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
