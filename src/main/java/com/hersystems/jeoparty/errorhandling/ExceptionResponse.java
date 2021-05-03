package com.hersystems.jeoparty.errorhandling;

public class ExceptionResponse {
    private String message;
    private int errorCode;

    public ExceptionResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ExceptionResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}