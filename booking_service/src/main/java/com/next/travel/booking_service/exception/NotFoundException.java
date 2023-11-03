package com.next.travel.booking_service.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }

}
