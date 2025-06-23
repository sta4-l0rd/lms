package com.sta4l0rd.lms.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.sta4l0rd.lms.DTOs.StudentDTO;
import com.sta4l0rd.lms.entity.BorrowHistory;
import com.sta4l0rd.lms.entity.Student;

public interface StudentService {
    public StudentDTO registerStudentDTO(StudentDTO studentDto);

    public Optional<Student> getStudentById(Long id);

    public StudentDTO getStudentDTOById(String id);

    public List<StudentDTO> getAllStudentsDTO();

    public List<StudentDTO> findStudentsDTOByString(String name);

    public StudentDTO updateStudentDTO(StudentDTO studentDto);

    public void deleteStudent(Long id);

    public Set<BorrowHistory> getBorrowHistory(Long studentId);

    public boolean hasActiveBorrowings(Long studentId);

    public int getCurrentBorrowedBooksCount(Long studentId);
}
