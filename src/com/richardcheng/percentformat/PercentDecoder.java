package com.richardcheng.percentformat;

import java.util.Hashtable;

public class PercentDecoder {
    private Hashtable<String, String> dictionary;

    public PercentDecoder() {
        dictionary = new Hashtable<>();
        dictionary.put("%20", " ");
        dictionary.put("%21", "!");
        dictionary.put("%22", "\"");
        dictionary.put("%23", "#");
        dictionary.put("%24", "\\$");
        dictionary.put("%26", "&");
        dictionary.put("%27", "'");
        dictionary.put("%28", "(");
        dictionary.put("%29", ")");
        dictionary.put("%2B", "+");
        dictionary.put("%2C", ",");
        dictionary.put("%2D", "-");
        dictionary.put("%2E", ".");
        dictionary.put("%3A", ":");
        dictionary.put("%3B", ";");
        dictionary.put("%3C", "<");
        dictionary.put("%3D", "=");
        dictionary.put("%3E", ">");
        dictionary.put("%3F", "?");
        dictionary.put("%40", "@");
        dictionary.put("%5B", "[");
        dictionary.put("%5C", "\\");
        dictionary.put("%5D", "]");
        dictionary.put("%5E", "^");
        dictionary.put("%5F", "_");
        dictionary.put("%60", "`");
        dictionary.put("%7B", "{");
        dictionary.put("%7C", "|");
        dictionary.put("%7D", "}");
        dictionary.put("%7E", "~");
    }

    public String decode(String encoded) {
        String decoded = encoded;

        for(String encodedSymbol: dictionary.keySet()) {
            decoded = decoded.replaceAll(encodedSymbol, dictionary.get(encodedSymbol));
        }

        return decoded;
    }
}
