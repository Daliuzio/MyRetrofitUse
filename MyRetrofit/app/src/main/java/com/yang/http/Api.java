package com.yang.http;

public interface Api {
    String SERVER_HOST = "http://192.168.101.78:8080/apitest/";

    String TEST = SERVER_HOST+"test";
    String LOGIN = SERVER_HOST+"login";
    String POST_JSON = SERVER_HOST+"postJson";
    String UPLOAD = SERVER_HOST+"upload";
    String UPLOAD_MULTI = SERVER_HOST+"uploadMulti";
    String IMAGE = SERVER_HOST+"image";
}
