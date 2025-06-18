package com.sta4l0rd.lms.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private LocalDate date_of_birth;

    @Column(columnDefinition = "CHAR(1)")
    private Character sex;

    @Column(columnDefinition = "VARCHAR(320)")
    @Email
    private String email;

    @Column(columnDefinition = "VARCHAR(25)")
    @Pattern(regexp = "^\\+\\d{1,3}-\\d{3}-\\d{3}-\\d{4}$")
    private String phone;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BorrowingHistory> studentBorrowingHistories = new HashSet<>();

}
