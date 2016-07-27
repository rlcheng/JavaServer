package com.richardcheng.javaserver;

/**
 * Created by richardcheng on 7/26/16.
 */
public interface IEndpoint {
    boolean match(String endpoint);
    String route(String httpMethod);
}
