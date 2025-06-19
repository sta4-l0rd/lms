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

    @Column(columnDefinition = "VARCHAR(320)")
    @Email
    private String email;

    @Column(columnDefinition = "VARCHAR(25)", unique = true)
    @Pattern(regexp = "^\\+\\d{1,3}-\\d{3}-\\d{3}-\\d{4}$")
    private String phone;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BorrowingHistory> studentBorrowingHistories = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate date_of_birth) {
        this.dateOfBirth = date_of_birth;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<BorrowingHistory> getStudentBorrowingHistories() {
        return studentBorrowingHistories;
    }

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
}
