package com.sta4l0rd.lms.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sta4l0rd.lms.DTOs.BookDTO;
import com.sta4l0rd.lms.serviceImpl.BookServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/v1/book")
@CrossOrigin(origins = "http://localhost:5173")
public class BookRestController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @GetMapping("")
    public ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>(bookServiceImpl.getAllBooksDTO(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createBook(@RequestBody BookDTO newBookDTO) {
        bookServiceImpl.addBookDTO(newBookDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {

        // bookServiceImpl.updateBookDTO();
        //TODO: process PUT request
        
        return entity;
    }
}
