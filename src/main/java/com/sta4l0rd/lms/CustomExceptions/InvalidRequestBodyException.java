package com.sta4l0rd.lms.CustomExceptions;

public class InvalidRequestBodyException extends RuntimeException {
    public InvalidRequestBodyException(String msg) {
        super("[Invalid Request Body][" + msg + "]");
    }
}
