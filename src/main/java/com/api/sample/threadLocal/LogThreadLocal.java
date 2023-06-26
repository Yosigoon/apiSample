package com.api.sample.threadLocal;

public class LogThreadLocal {

    private LogThreadLocal() {}
    public static ThreadLocal<String> myLogThreadLocal = new ThreadLocal<>();

}
