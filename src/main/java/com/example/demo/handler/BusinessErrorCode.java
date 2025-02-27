package com.example.demo.handler;

import org.springframework.http.HttpStatus;

public enum BusinessErrorCode {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "Account is locked"),
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "incorrect password"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "password not match"),
    ACCOUNT_DISABLED(302, HttpStatus.FORBIDDEN, "disabled"),
    BAD_CREDENTIALS(304, HttpStatus.FORBIDDEN, "credential incorrect"),

    ;
    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCode(int code, HttpStatus httpStatus,  String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
