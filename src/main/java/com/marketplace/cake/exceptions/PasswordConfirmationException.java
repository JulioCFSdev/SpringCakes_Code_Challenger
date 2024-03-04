package com.marketplace.cake.exceptions;

public class PasswordConfirmationException extends RuntimeException{
    public PasswordConfirmationException() { super("Password and confirmation password do not match.");}
    public PasswordConfirmationException(String message) { super(message);}
}
