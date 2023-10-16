package com.next.travel.user_service.exception;

public class InvalidException extends RuntimeException{
    public InvalidException(String message){
        super(message);
    }
}