package com.next.travel.auth_service.exception;


public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}
