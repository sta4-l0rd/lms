package com.sta4l0rd.lms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sta4l0rd.lms.DTOs.RuntimeErrorDTO;
import com.sta4l0rd.lms.DTOs.RuntimeExceptionDTO;
import com.sta4l0rd.lms.entity.Student;
import com.sta4l0rd.lms.serviceImpl.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    private static Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @PostMapping("/new")
    public ResponseEntity<?> registerStudent(@RequestBody Student newStudent) {
        try {
            studentServiceImpl.registerStudent(newStudent);
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new RuntimeExceptionDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
