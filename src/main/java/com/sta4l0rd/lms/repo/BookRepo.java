package com.sta4l0rd.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sta4l0rd.lms.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long>{

}
