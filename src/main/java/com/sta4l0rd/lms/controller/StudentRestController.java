package com.sta4l0rd.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sta4l0rd.lms.entity.Student;
import com.sta4l0rd.lms.serviceImpl.StudentServiceImpl;

@RestController
@RequestMapping("api/student")
public class StudentRestController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @PostMapping("/new")
    public ResponseEntity<?> registerStudent(@RequestBody Student newStudent) {
        studentServiceImpl.registerStudent(newStudent);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(studentServiceImpl.getAllStudents(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student newStudent) {
        return new ResponseEntity<>(studentServiceImpl.updateStudent(newStudent), HttpStatus.CREATED);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<?> findStudent(@PathVariable String name) {
        return new ResponseEntity<>(studentServiceImpl.findStudentsByName(name), HttpStatus.OK);
    }
}
