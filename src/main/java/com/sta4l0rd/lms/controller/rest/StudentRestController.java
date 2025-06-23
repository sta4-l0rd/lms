package com.sta4l0rd.lms.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sta4l0rd.lms.DTOs.StudentDTO;
import com.sta4l0rd.lms.serviceImpl.StudentServiceImpl;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentRestController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(studentServiceImpl.getAllStudentsDTO(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findStudent(@RequestParam(name = "searchString") String searchString) {
        return new ResponseEntity<>(studentServiceImpl.findStudentsDTOByString(searchString), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> registerStudent(@RequestBody StudentDTO newStudentDTO) {
        return new ResponseEntity<>(studentServiceImpl.registerStudentDTO(newStudentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO res = studentServiceImpl.updateStudentDTO(studentDTO);
        if (res != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return registerStudent(studentDTO);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        studentServiceImpl.deleteStudent(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
