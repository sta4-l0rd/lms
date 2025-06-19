package com.sta4l0rd.lms.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sta4l0rd.lms.CustomExceptions.InvalidRequestBodyException;
import com.sta4l0rd.lms.CustomExceptions.ResourceNotFoundException;
import com.sta4l0rd.lms.DTOs.RuntimeExceptionDTO;
import com.sta4l0rd.lms.controller.StudentRestController;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(StudentRestController.class);

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
