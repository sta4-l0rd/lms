package com.sta4l0rd.lms.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(unique = true)
  private String isbn;

  private Integer total_copies;
  private Integer available_copies;

  @OneToMany(mappedBy = "book")
  private Set<BorrowingHistory> bookBorrowingHistories = new HashSet<>();
}
