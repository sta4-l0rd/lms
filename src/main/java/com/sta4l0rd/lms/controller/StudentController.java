package com.sta4l0rd.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        studentServiceImpl.registerStudentDTO(studentDTO);
        return "redirect:/student/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditStudentForm(@PathVariable String id, Model model) {
        model.addAttribute("student", studentServiceImpl.getStudentDTOById(id));
        return "student-form";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@ModelAttribute("student") StudentDTO studentDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "student-form";
        }
        studentServiceImpl.updateStudentDTO(studentDTO);
        return "redirect:/student/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentServiceImpl.deleteStudent(id);
        return "redirect:/student/all";
    }

    @GetMapping("/search/{str}")
    public String searchStudent(@PathVariable String str, Model model) {
        model.addAttribute("students", studentServiceImpl.findStudentsDTOByString(str));
        return "students-list";

    }
}
