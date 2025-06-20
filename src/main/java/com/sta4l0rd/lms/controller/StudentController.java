package com.sta4l0rd.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sta4l0rd.lms.DTOs.StudentDTO;
import com.sta4l0rd.lms.serviceImpl.StudentServiceImpl;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @GetMapping("/all")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentServiceImpl.getAllStudentsDTO());
        return "students-list";
    }

    @GetMapping("/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new StudentDTO());
        return "student-form";
    }

    @PostMapping("/new")
    public String registerStudent(@ModelAttribute("student") StudentDTO studentDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "student-form";
        }
        studentServiceImpl.registerStudent(studentDTO);
        return "redirect:/student/all";
    }
    // GetMapping("/edit{phone}")
}
