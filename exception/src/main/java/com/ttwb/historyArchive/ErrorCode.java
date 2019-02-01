package com.ttwb.historyArchive;

/**
 * Created by user on 2017/1/17.
 */
public enum  ErrorCode {

    unauthenticated(1001, "用户没有认证"),
    unauthorized(1002, "用户没有权限"),
    missAccessToken(1003, "缺少AccessToken"),
    invalidUser(1004, "用户名或密码有误");

    private int code;

    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
