package com.sta4l0rd.lms.serviceImpl;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sta4l0rd.lms.CustomExceptions.InvalidRequestBodyException;
import com.sta4l0rd.lms.entity.BorrowingHistory;
import com.sta4l0rd.lms.entity.Student;
import com.sta4l0rd.lms.repo.StudentRepo;
import com.sta4l0rd.lms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    private void validateStudent(Student student) {
        if (student == null) {
            throw new InvalidRequestBodyException("Student cannot be null");
        }

        if (student.getFirst_name() == null || student.getFirst_name().trim().isEmpty()) {
            throw new InvalidRequestBodyException("First name is required");
        }

        if (student.getPhone() == null || student.getPhone().trim().isEmpty()) {
            throw new InvalidRequestBodyException("Phone number is required");
        }
    }

    private void validatePhoneNumberFormat(String phone) {
        Pattern pattern = Pattern.compile("^\\+\\d{1,3}-\\d{3}-\\d{3}-\\d{4}$");
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            throw new InvalidRequestBodyException("Phone number must follow format: +{COUNTRY_CODE}-XXX-XXX-XXXX.");
        }
    }

    private void checkForDuplicates(Student student) {
        if (studentRepo.existsByPhone(student.getPhone())) {
            throw new InvalidRequestBodyException("Phone number already exists");
        }

        if (student.getEmail() != null && !student.getEmail().trim().isEmpty()
                && studentRepo.existsByEmail(student.getEmail())) {
            throw new InvalidRequestBodyException("Email already exists");
        }
    }

    @Override
    public Student registerStudent(Student student) {
        validateStudent(student);
        validatePhoneNumberFormat(student.getPhone());
        checkForDuplicates(student);

        return studentRepo.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentById'");
    }

    @Override
    public List<Student> getAllStudents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStudents'");
    }

    @Override
    public Student updateStudent(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStudent'");
    }

    @Override
    public void deleteStudent(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudent'");
    }

    @Override
    public List<Student> searchStudentsByLastName(String lastName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchStudentsByLastName'");
    }

    @Override
    public List<Student> searchStudentsByFirstName(String firstName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchStudentsByFirstName'");
    }

    @Override
    public Student findStudentByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findStudentByEmail'");
    }

    @Override
    public Student findStudentByPhone(String phone) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findStudentByPhone'");
    }

    @Override
    public Set<BorrowingHistory> getBorrowingHistory(Long studentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBorrowingHistory'");
    }

    @Override
    public boolean hasActiveBorrowings(Long studentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasActiveBorrowings'");
    }

    @Override
    public List<Student> getStudentsWithOverdueBooks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsWithOverdueBooks'");
    }

    @Override
    public int getCurrentBorrowedBooksCount(Long studentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentBorrowedBooksCount'");
    }

}
