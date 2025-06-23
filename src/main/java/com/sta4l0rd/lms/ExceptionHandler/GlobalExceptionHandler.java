package com.sta4l0rd.lms.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sta4l0rd.lms.CustomExceptions.InvalidRequestBodyException;
import com.sta4l0rd.lms.CustomExceptions.ResourceNotFoundException;
import com.sta4l0rd.lms.DTOs.RuntimeExceptionDTO;
import com.sta4l0rd.lms.controller.StudentRestController;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, message);
        });

        return new ResponseEntity<>(new RuntimeExceptionDTO(errors.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<?> handleInvalidRequestBody(InvalidRequestBodyException ex) {
        logger.error("[handleInvalidRequestBody]" + ex.getMessage());
        return new ResponseEntity<>(new RuntimeExceptionDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
