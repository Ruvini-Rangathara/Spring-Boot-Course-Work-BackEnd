package com.next.travel.booking_service.exception;

public class InvalidException extends RuntimeException{
    public InvalidException(String message){
        super(message);
    }
}