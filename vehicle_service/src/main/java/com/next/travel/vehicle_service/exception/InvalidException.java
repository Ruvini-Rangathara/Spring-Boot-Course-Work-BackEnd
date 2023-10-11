package com.next.travel.vehicle_service.exception;

public class InvalidException extends RuntimeException{
    public InvalidException(String message){
        super(message);
    }
}