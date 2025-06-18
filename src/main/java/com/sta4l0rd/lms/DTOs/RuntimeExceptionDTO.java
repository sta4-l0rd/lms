package com.sta4l0rd.lms.DTOs;

public class RuntimeExceptionDTO {
    String error;

    public RuntimeExceptionDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
