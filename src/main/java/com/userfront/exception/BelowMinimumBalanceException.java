package com.userfront.exception;

public class BelowMinimumBalanceException extends Exception{
    public BelowMinimumBalanceException(String message) {
        super(message);
    }
}
