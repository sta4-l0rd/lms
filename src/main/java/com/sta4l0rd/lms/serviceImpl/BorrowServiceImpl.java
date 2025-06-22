package com.sta4l0rd.lms.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sta4l0rd.lms.CustomExceptions.InvalidRequestBodyException;
import com.sta4l0rd.lms.DTOs.BookDTO;
import com.sta4l0rd.lms.DTOs.BorrowHistoryDTO;
import com.sta4l0rd.lms.entity.Book;
import com.sta4l0rd.lms.entity.BorrowHistory;
import com.sta4l0rd.lms.entity.Student;
import com.sta4l0rd.lms.enums.BookStatus;
import com.sta4l0rd.lms.repo.BorrowHistoryRepo;
import com.sta4l0rd.lms.service.BorrowService;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final ModelMapper modelMapper;

    public BorrowServiceImpl() {
        this.modelMapper = new ModelMapper();
        configureModelMapper();
    }

    private void configureModelMapper() {
        configureBorrowHistoryToDtoMapping();
        // configureDtoToBookMapping();
    }

    private void configureBorrowHistoryToDtoMapping() {
        TypeMap<BorrowHistory, BorrowHistoryDTO> typeMap = modelMapper.createTypeMap(BorrowHistory.class,
                BorrowHistoryDTO.class);
        typeMap.addMappings(mapper -> {
            mapper.map(BorrowHistory::getId, BorrowHistoryDTO::setBorrowHistoryIdString);
            mapper.map(src -> src.getBook().getId(), BorrowHistoryDTO::setBookIdString);
            mapper.map(src -> src.getBook().getTitle(), BorrowHistoryDTO::setBookTitleString);
            mapper.map(src -> src.getBook().getAuthor(), BorrowHistoryDTO::setBookAuthorString);
            mapper.map(src -> src.getStudent().getId(), BorrowHistoryDTO::setStudentIdString);
            mapper.map(src -> src.getStudent().getFirstName(), BorrowHistoryDTO::setStudentFirstNameString);
            mapper.map(src -> src.getStudent().getLastName(), BorrowHistoryDTO::setStudentLastNameString);
            mapper.map(BorrowHistory::getBorrowDate, BorrowHistoryDTO::setBorrowDateString);
            mapper.map(BorrowHistory::getDueDate, BorrowHistoryDTO::setDueDateString);
            mapper.map(BorrowHistory::getReturnDate, BorrowHistoryDTO::setReturnDateString);
            mapper.map(BorrowHistory::getStatus, BorrowHistoryDTO::setBookStatusString);
        });
    }

    @Autowired
    private BorrowHistoryRepo borrowHistoryRepo;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    public BorrowHistoryDTO issueBook(Long studentId, Long bookId) {
        Optional<Book> book = bookServiceImpl.getBookById(bookId);
        Optional<Student> student = studentServiceImpl.getStudentById(studentId);
        if (book.isPresent() && student.isPresent()) {
            List<BorrowHistory> borrowingHistory = borrowHistoryRepo.findByStudentIdAndBookId(studentId, bookId);
            if (borrowingHistory == null || borrowingHistory.isEmpty()) {
                BorrowHistory borrowHistory = new BorrowHistory();
                borrowHistory.setBook(book.get());
                borrowHistory.setStudent(student.get());
                borrowHistory.setBorrowDate(LocalDate.now());
                borrowHistory.setDueDate(borrowHistory.getBorrowDate().plusDays(15));
                borrowHistory.setStatus(BookStatus.CHECKED_OUT);
                bookServiceImpl.updateAvailableCopies(bookId, -1);
                borrowHistoryRepo.save(borrowHistory);
                return modelMapper.map(borrowHistory, BorrowHistoryDTO.class);
            } else {
                throw new InvalidRequestBodyException("Book is already checked out with the given student");
            }
        } else {
            throw new InvalidRequestBodyException("Cannont Find given Book or Student in database");
        }
    }

    public List<BorrowHistoryDTO> getBorrowingHistoryDTO() {
        return borrowHistoryRepo.findAll().stream()
                .map(borrowHistory -> modelMapper.map(borrowHistory, BorrowHistoryDTO.class))
                .collect(Collectors.toList());
    }

    public void returnBook(Long borrowId) {
        borrowHistoryRepo.findById(borrowId).ifPresent(record -> {
            bookServiceImpl.updateAvailableCopies(record.getBook().getId(), 1);
            record.setReturnDate(LocalDate.now());
            record.setStatus(BookStatus.RETURNED);
            borrowHistoryRepo.save(record);
        });
    }
}
