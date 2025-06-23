package com.sta4l0rd.lms.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.sta4l0rd.lms.DTOs.BookDTO;
import com.sta4l0rd.lms.entity.Book;
import com.sta4l0rd.lms.entity.BorrowHistory;

public interface BookService {
    public BookDTO addBookDTO(BookDTO bookDto);

    public BookDTO getBookDTObyId(Long id);

    public Optional<Book> getBookById(Long id);

    public List<BookDTO> getAllBooksDTO();

    public List<BookDTO> findBooksDTO(String searchString);

    public Book getBookByIsbn(String isbn);

    public BookDTO updateBookDTO(BookDTO bookDTO);

    public void deleteBook(Long id);

    public List<Book> getAvailableBooks();

    public Optional<Book> updateAvailableCopies(Long bookId, int change);

    public Set<BorrowHistory> getBorrowHistory(Long bookId);

}
