package com.sta4l0rd.lms.serviceImpl;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.EmailValidator;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sta4l0rd.lms.CustomExceptions.InvalidRequestBodyException;
import com.sta4l0rd.lms.DTOs.StudentDTO;
import com.sta4l0rd.lms.entity.BorrowingHistory;
import com.sta4l0rd.lms.entity.Student;
import com.sta4l0rd.lms.repo.StudentRepo;
import com.sta4l0rd.lms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    private final ModelMapper modelMapper;

    public StudentServiceImpl() {
        this.modelMapper = new ModelMapper();
        configureModelMapper();
    }

    private void configureModelMapper() {
        configureStudentToDtoMapping();
        configureDtoToStudentMapping();
    }

    private void configureStudentToDtoMapping() {
        if (modelMapper.getTypeMap(Student.class, StudentDTO.class) == null) {
            TypeMap<Student, StudentDTO> typeMap = modelMapper.createTypeMap(Student.class, StudentDTO.class);
            typeMap.addMappings(mapper -> {
                mapper.map(Student::getGender, StudentDTO::setGender);
                mapper.when(Conditions.isNull()).skip(Student::getEmail, StudentDTO::setEmail);
            });
        }
    }

    private void configureDtoToStudentMapping() {
        if (modelMapper.getTypeMap(StudentDTO.class, Student.class) == null) {
            TypeMap<StudentDTO, Student> typeMap = modelMapper.createTypeMap(StudentDTO.class, Student.class);
            typeMap.addMappings(mapper -> {
                mapper.map(StudentDTO::getGender, Student::setGender);
            });
        }
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new InvalidRequestBodyException("Student cannot be null");
        }

        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
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

    private void validateEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(email)) {
            throw new InvalidRequestBodyException("Invalid Email");
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
        validateEmail(student.getEmail());
        checkForDuplicates(student);
        try {
            return studentRepo.save(student);
        } catch (Exception exc) {
            throw new InvalidRequestBodyException(exc.getMessage());
        }
    }

    public StudentDTO registerStudent(StudentDTO studentDto) {
        Student student = new Student();
        student = modelMapper.map(studentDto, Student.class);

        validateStudent(student);
        validatePhoneNumberFormat(student.getPhone());
        checkForDuplicates(student);
        studentRepo.save(student);
        return studentDto;
    }

    @Override
    public Student getStudentById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentById'");
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public List<StudentDTO> getAllStudentsDTO() {
        return studentRepo.findAll().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Student updateStudent(Student student) {
        validateStudent(student);
        validatePhoneNumberFormat(student.getPhone());
        Student existingRecord = studentRepo.findByPhone(student.getPhone());
        if (existingRecord != null) {
            student.setId(existingRecord.getId());
            studentRepo.save(student);
            return student;
        } else {
            return null;
        }
    }

    public StudentDTO updateStudentDTO(StudentDTO studentDto) {
        Student student = new Student();
        student = modelMapper.map(studentDto, Student.class);
        validateStudent(student);
        validatePhoneNumberFormat(student.getPhone());
        Student existingRecord = studentRepo.findByPhone(student.getPhone());
        if (existingRecord != null) {
            student.setId(existingRecord.getId());
            studentRepo.save(student);
            return studentDto;
        } else {
            return null;
        }
    }

    @Override
    public void deleteStudent(Long id) {
        if (studentRepo.findById(id) != null) {
            studentRepo.deleteById(id);
        }
    }

    @Override
    public List<Student> findStudentsByString(String name) {
        return studentRepo.findByFirstNameContainingOrLastNameContainingOrEmailContainingOrPhoneContainingAllIgnoringCase(name, name, name, name);
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

    public StudentDTO getStudentDTOById(String id) {
        return modelMapper.map(studentRepo.findById(Long.parseLong(id)), StudentDTO.class);
    }

}
