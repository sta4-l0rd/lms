package com.sta4l0rd.lms.CustomExceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String msg) {
        super(msg);
    }
}
