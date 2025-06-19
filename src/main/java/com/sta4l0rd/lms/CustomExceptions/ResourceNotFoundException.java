package com.sta4l0rd.lms.CustomExceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super("[Resource Not Found][" + msg + "]");
    }
}
