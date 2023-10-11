package com.next.travel.hotel_service.exception;

public class InvalidException extends RuntimeException{
    public InvalidException(String message){
        super(message);
    }
}