package com.sta4l0rd.lms.service;
import java.util.List;
import java.util.Set;

import com.sta4l0rd.lms.entity.BorrowingHistory;
import com.sta4l0rd.lms.entity.Student;

public interface StudentService {
    public Student registerStudent(Student student);

    public Student getStudentById(Long id);
    public List<Student> getAllStudents();

    public Student updateStudent(Student student);
    public void deleteStudent(Long id);

    List<Student> searchStudentsByLastName(String lastName);
    List<Student> searchStudentsByFirstName(String firstName);

    Student findStudentByEmail(String email);
    Student findStudentByPhone(String phone);

    public Set<BorrowingHistory> getBorrowingHistory(Long studentId);

    public boolean hasActiveBorrowings(Long studentId);
    public List<Student> getStudentsWithOverdueBooks();
    public int getCurrentBorrowedBooksCount(Long studentId);
}
