package com.example.heapspring.exceptions;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message){
        super(message,null, false, false);
    }
}
