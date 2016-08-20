package com.richardcheng.javaserver;

import java.util.HashMap;

public class ServerArgumentHelper {
    private int port;
    private String path;
    private String[] args;

    public ServerArgumentHelper(String[] args) {
        this.args = args;
    }

    public void parseArgs() {
        HashMap<String, String> argsHash = new HashMap<>();

        if (args.length % 2 == 0) {
            argsHash = arrayToHash();
        }

        port = Integer.parseInt(argsHash.getOrDefault("-p", "5000"));
        path = argsHash.getOrDefault("-d", "");
    }

    private HashMap<String, String> arrayToHash() {
        HashMap<String, String> argsHash = new HashMap<>();

        for (int i = 0; i < (args.length - 1); i++) {
            argsHash.put(args[i], args[i+1]);
        }

        return argsHash;
    }

    public int port() {
        return port;
    }

    public String path() {
        return path;
    }
}
