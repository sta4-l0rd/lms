package com.sta4l0rd.lms.entity;

import java.time.LocalDate;

import com.sta4l0rd.lms.enums.BookStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@lombok.Data
@Entity
public class BorrowingHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  private LocalDate borrow_date;

  // Remeber to make it auto configurable days from borrow_date
  private LocalDate due_date;

  @Enumerated(EnumType.STRING)
  private BookStatus status;

}
