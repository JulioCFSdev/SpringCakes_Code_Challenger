package com.marketplace.cake.exceptions;

public class EmailNotValidException extends RuntimeException{
    public EmailNotValidException() { super("Email Not is valid");}
    public EmailNotValidException(String message) { super(message);}
}
