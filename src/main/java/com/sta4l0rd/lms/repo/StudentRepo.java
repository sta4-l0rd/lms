package com.sta4l0rd.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sta4l0rd.lms.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    public boolean existsByPhone(String phone);
    public boolean existsByEmail(String email);
    public Student findByPhone(String phone);
    public List<Student> findByFirstNameOrLastNameOrEmailOrPhone(String firstName, String lastName, String email, String phone);
}
