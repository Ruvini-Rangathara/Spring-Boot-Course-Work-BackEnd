package com.next.travel.package_service.exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String message){
        super(message);
    }
}
