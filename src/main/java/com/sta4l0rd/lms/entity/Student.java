package com.sta4l0rd.lms.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import com.sta4l0rd.lms.enums.Gender;

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

@lombok.Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @Column(columnDefinition = "CHAR(1)")
    @ColumnDefault("0")
    private Character sex = '0';

    @Column(columnDefinition = "VARCHAR(320)", unique = true)
    @Email
    private String email;

    @Column(columnDefinition = "VARCHAR(25)", unique = true)
    @Pattern(regexp = "^\\+\\d{1,3}-\\d{3}-\\d{3}-\\d{4}$")
    private String phone;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BorrowHistory> studentBorrowingHistories = new HashSet<>();

    public Gender getGender() {
        if (this.sex == '0') {
            return Gender.NOT_KNOWN;
        } else if (this.sex == '1') {
            return Gender.MALE;
        } else if (this.sex == '2') {
            return Gender.FEMALE;
        } else
            return Gender.NOT_APPLICABLE;
    }

    public void setGender(Gender gender) {
        if (gender == Gender.NOT_KNOWN) {
            this.sex = '0';
        } else if (gender == Gender.MALE) {
            this.sex = '1';
        } else if (gender == Gender.FEMALE) {
            this.sex = '2';
        } else {
            this.sex = '9';
        }
    }
}
