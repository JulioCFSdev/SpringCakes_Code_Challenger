package com.marketplace.cake.exceptions;

public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException() { super("Data Resource is already registered!");}
    public DuplicateResourceException(String message) { super(message);}
}
