package com.marketplace.cake.exceptions;

public class ArgumentNotValidException extends RuntimeException{
    public ArgumentNotValidException() { super("Email Not is valid");}
    public ArgumentNotValidException(String message) { super(message);}
}
