package com.next.travel.booking_service.exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String message){
        super(message);
    }
}
