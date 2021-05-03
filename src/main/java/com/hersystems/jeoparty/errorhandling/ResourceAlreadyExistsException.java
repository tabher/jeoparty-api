package com.hersystems.jeoparty.errorhandling;

public class ResourceAlreadyExistsException extends Exception {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
