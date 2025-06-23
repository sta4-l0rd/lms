package com.sta4l0rd.lms.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sta4l0rd.lms.DTOs.BookDTO;
import com.sta4l0rd.lms.serviceImpl.BookServiceImpl;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @GetMapping("/all")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookServiceImpl.getAllBooksDTO());
        return "books-list";
    }

    @GetMapping("/new")
    public String showNewBookForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "book-form";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") BookDTO bookDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book-form";
        }
        bookServiceImpl.addBookDTO(bookDTO);
        return "redirect:/book/all";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookServiceImpl.getBookDTObyId(id));
        return "book-form";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@ModelAttribute("book") BookDTO bookDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book-form";
        }
        bookServiceImpl.updateBookDTO(bookDTO);
        return "redirect:/book/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookServiceImpl.deleteBook(id);
        return "redirect:/book/all";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(name = "searchString") String id, Model model) {
        model.addAttribute("books", bookServiceImpl.findBooksDTO(id));
        return "books-list";
    }
}
