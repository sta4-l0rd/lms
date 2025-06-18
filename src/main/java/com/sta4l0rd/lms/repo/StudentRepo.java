package com.sta4l0rd.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sta4l0rd.lms.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

}
