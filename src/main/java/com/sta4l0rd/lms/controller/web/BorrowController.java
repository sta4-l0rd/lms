package com.sta4l0rd.lms.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sta4l0rd.lms.DTOs.StudentDTO;
import com.sta4l0rd.lms.serviceImpl.BookServiceImpl;
import com.sta4l0rd.lms.serviceImpl.BorrowServiceImpl;
import com.sta4l0rd.lms.serviceImpl.StudentServiceImpl;

@Controller
@RequestMapping("borrow")
public class BorrowController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    private BorrowServiceImpl borrowServiceImpl;

    @GetMapping("")
    public String getIssueBookPage(Model model) {
        return "book-issue";
    }

    @GetMapping("student/search")
    public String searchStudents(@RequestParam(name = "searchString") String id, Model model) {
        model.addAttribute("students", studentServiceImpl.findStudentsDTOByString(id));
        return "book-issue";
    }

    @GetMapping("student")
    public String getStudentDetails(@RequestParam(name = "studentId") String id, Model model) {
        model.addAttribute("student", studentServiceImpl.getStudentDTOById(id));
        return "book-issue";
    }

    @PostMapping("book/search")
    public String searchBooks(@RequestParam(name = "searchString") String id,
            @ModelAttribute("student") StudentDTO studentDTO,
            BindingResult result, Model model) {
        model.addAttribute("student", studentDTO);
        model.addAttribute("books", bookServiceImpl.findBooksDTO(id));
        return "book-issue";
    }

    @GetMapping("issue")
    public String issueBook(@RequestParam(name = "studentId") String studentId,
            @RequestParam(name = "bookId") String bookId,
            Model model) {
        model.addAttribute("BorrowHistory",
                borrowServiceImpl.issueBook(Long.parseLong(studentId), Long.parseLong(bookId)));
        return "book-issue-success";
    }

    @GetMapping("history")
    public String getHistory(Model model) {
        model.addAttribute("BorrowingHistory", borrowServiceImpl.getBorrowingHistoryDTO());
        return "borrow-history";
    }

    @GetMapping("return")
    public String returnBook(@RequestParam(name = "id") String borrowId, Model model) {
        borrowServiceImpl.returnBook(Long.valueOf(borrowId));
        return "redirect:/borrow/history";
    }
}
