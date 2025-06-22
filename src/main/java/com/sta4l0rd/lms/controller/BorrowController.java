package com.sta4l0rd.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sta4l0rd.lms.DTOs.BookDTO;
import com.sta4l0rd.lms.DTOs.StudentDTO;
import com.sta4l0rd.lms.serviceImpl.BookServiceImpl;
import com.sta4l0rd.lms.serviceImpl.StudentServiceImpl;

@Controller
@RequestMapping("borrow")
public class BorrowController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping("")
    public String getIssueBookPage(Model model) {
        return "issue-book";
    }

    @GetMapping("student/search")
    public String searchStudents(@RequestParam(name = "searchString") String id, Model model) {
        model.addAttribute("students", studentServiceImpl.findStudentsByString(id));
        return "issue-book";
    }

    @GetMapping("student")
    public String getStudentDetails(@RequestParam(name = "studentId") String id, Model model) {
        model.addAttribute("student", studentServiceImpl.getStudentDTOById(id));
        return "issue-book";
    }

    @PostMapping("book/search")
    public String searchBooks(@RequestParam(name = "searchString") String id,
            @ModelAttribute("student") StudentDTO studentDTO,
            BindingResult result, Model model) {
        model.addAttribute("student", studentDTO);
        model.addAttribute("books", bookServiceImpl.findBooksDTO(id));
        return "issue-book";
    }

    @GetMapping("issue")
    public ResponseEntity<?> issueBook(@RequestParam(name = "studentId") String studentId,
            @RequestParam(name = "bookId") String bookId,
            Model model) {
        return new ResponseEntity<>("Book Issued " + studentId + bookId, HttpStatus.OK);
        // model.addAttribute("student", studentServiceImpl.getStudentDTOById(id));
        // return "issue-book";
    }
}
