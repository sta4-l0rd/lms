package com.sta4l0rd.lms.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.ISBNValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sta4l0rd.lms.CustomExceptions.InvalidRequestBodyException;
import com.sta4l0rd.lms.DTOs.BookDTO;
import com.sta4l0rd.lms.entity.Book;
import com.sta4l0rd.lms.entity.BorrowHistory;
import com.sta4l0rd.lms.repo.BookRepo;
import com.sta4l0rd.lms.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    private final ModelMapper modelMapper;

    public BookServiceImpl() {
        this.modelMapper = new ModelMapper();
        configureModelMapper();
    }

    private void configureModelMapper() {
        configureBookToDtoMapping();
        // configureDtoToBookMapping();
    }

    private void configureBookToDtoMapping() {
        TypeMap<Book, BookDTO> typeMap = modelMapper.createTypeMap(Book.class, BookDTO.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Book::getId, BookDTO::setId);
            mapper.map(Book::getTotalCopies, BookDTO::setTotalCopies);
            mapper.map(Book::getAvailableCopies, BookDTO::setAvailableCopies);
        });
    }

    private Book getValidatedBook(Book book) {
        if (book == null) {
            throw new InvalidRequestBodyException("Book cannot be null");
        }

        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new InvalidRequestBodyException("ISBN is required");
        }

        String vaildISBN = ISBNValidator.getInstance().validate(book.getIsbn());
        if (vaildISBN == null || vaildISBN.isEmpty()) {
            throw new InvalidRequestBodyException("Invalid ISBN");
        }

        if (book.getTotalCopies() == null) {
            book.setTotalCopies(0);
            book.setAvailableCopies(0);
        }
        if (book.getAvailableCopies() == null) {
            book.setAvailableCopies(0);
        }
        if (book.getTotalCopies() != null && book.getAvailableCopies() != null) {
            if (book.getAvailableCopies() > book.getTotalCopies()) {
                throw new InvalidRequestBodyException("Available Copies cannot be more than Total Copies");
            }
        }

        return book;
    }

    /*
     * Service methods that handles and process DTOs
     */
    @Override
    public BookDTO addBookDTO(BookDTO bookDto) {
        Book book = new Book();
        book = modelMapper.map(bookDto, Book.class);

        bookRepo.save(getValidatedBook(book));
        return bookDto;
    }

    @Override
    public List<BookDTO> getAllBooksDTO() {
        return bookRepo.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBookDTO(BookDTO bookDTO) {
        Book book = new Book();
        book = this.modelMapper.map(bookDTO, Book.class);
        book = getValidatedBook(book);
        bookRepo.save(book);
        return this.modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO getBookDTObyId(Long id) {
        return this.modelMapper.map(bookRepo.findById(id), BookDTO.class);
    }

    @Override
    public List<BookDTO> findBooksDTO(String searchString) {
        return bookRepo.findByTitleContainingOrAuthorContainingAllIgnoringCase(searchString, searchString).stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    /*
     * Service methods that handles and process entity
     */
    @Override
    public void deleteBook(Long id) {
        if (bookRepo.findById(id) != null) {
            bookRepo.deleteById(id);
        }
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    @Override
    public Optional<Book> updateAvailableCopies(Long bookId, int change) {
        if (bookId != null) {
            Optional<Book> book = bookRepo.findById(bookId);
            book.ifPresent(b -> {
                b.setAvailableCopies(b.getAvailableCopies() + change);
                bookRepo.save(b);
            });
            return book;
        } else {
            throw new RuntimeException("bookId must not be null");
        }
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookByIsbn'");
    }

    @Override
    public List<Book> getAvailableBooks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableBooks'");
    }

    @Override
    public Set<BorrowHistory> getBorrowHistory(Long bookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBorrowHistory'");
    }
}
