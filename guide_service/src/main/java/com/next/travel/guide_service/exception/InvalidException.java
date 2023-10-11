package com.next.travel.guide_service.exception;

public class InvalidException extends RuntimeException{
    public InvalidException(String message){
        super(message);
    }
}