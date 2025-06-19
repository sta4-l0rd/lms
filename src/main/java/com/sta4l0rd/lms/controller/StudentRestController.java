package com.sta4l0rd.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sta4l0rd.lms.CustomExceptions.InvalidRequestBodyException;
import com.sta4l0rd.lms.entity.Student;
import com.sta4l0rd.lms.serviceImpl.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @PostMapping("/new")
    public ResponseEntity<?> registerStudent(@RequestBody Student newStudent) throws InvalidRequestBodyException {
        studentServiceImpl.registerStudent(newStudent);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }
}
