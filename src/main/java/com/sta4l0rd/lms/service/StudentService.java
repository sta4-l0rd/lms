package com.sta4l0rd.lms.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.sta4l0rd.lms.entity.BorrowHistory;
import com.sta4l0rd.lms.entity.Student;

public interface StudentService {
    public Student registerStudent(Student student);

    public Optional<Student> getStudentById(Long id);
    public List<Student> getAllStudents();

    public Student updateStudent(Student student);
    public void deleteStudent(Long id);

    List<Student> findStudentsByString(String name);

    Student findStudentByEmail(String email);
    Student findStudentByPhone(String phone);

    public Set<BorrowHistory> getBorrowHistory(Long studentId);

    public boolean hasActiveBorrowings(Long studentId);
    public List<Student> getStudentsWithOverdueBooks();
    public int getCurrentBorrowedBooksCount(Long studentId);
}
