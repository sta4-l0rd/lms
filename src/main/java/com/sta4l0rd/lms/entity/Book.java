package com.sta4l0rd.lms.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@lombok.Data
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String author;

  @Column(unique = true)
  private String isbn;

  @ColumnDefault("0")
  private Integer totalCopies;
  @ColumnDefault("0")
  private Integer availableCopies;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<BorrowingHistory> bookBorrowingHistories = new HashSet<>();
}
