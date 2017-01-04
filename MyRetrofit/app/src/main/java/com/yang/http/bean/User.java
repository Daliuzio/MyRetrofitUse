package com.yang.http.bean;

/**
 * Created by lxj on 2016/12/28.
 * {"username":"俊哥",
 * "usertoken":"6e416e5f-67a8-445f-a18a-1d8257f3fa07",
 * "password":"111"}
 */

public class User {
    public String username;
    public String usertoken;
    public String password;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", usertoken='" + usertoken + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
