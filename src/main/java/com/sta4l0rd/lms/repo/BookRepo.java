package com.sta4l0rd.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sta4l0rd.lms.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long>{
    public List<Book> findByTitleOrAuthor(String title, String author);
    public List<Book> findByTitleContainingOrAuthorContainingAllIgnoringCase(String title, String author);
}
