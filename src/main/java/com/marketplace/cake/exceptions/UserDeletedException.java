package com.marketplace.cake.exceptions;

public class UserDeletedException extends RuntimeException{
    public UserDeletedException() {super("User is deleted.");}
    public UserDeletedException(String message) {super(message);}
}
