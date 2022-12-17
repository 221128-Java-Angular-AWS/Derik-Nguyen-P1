package com.revature.exceptions;

public class UsernameTaken extends Exception{
    public UsernameTaken (String msg) {
        super(msg);
    }
}
