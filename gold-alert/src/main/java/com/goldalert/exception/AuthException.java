package com.goldalert.exception;

import org.springframework.http.HttpStatus;

public class AuthException extends RuntimeException {

    private final String code;

    public AuthException(String message, String code) {
        super(message);
        this.code = code;
    }

    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    public String getCode() {
        return code;
    }
}
