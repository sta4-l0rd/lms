package com.sta4l0rd.lms.service;

import java.util.List;
import java.util.Set;

import com.sta4l0rd.lms.entity.Book;
import com.sta4l0rd.lms.entity.BorrowingHistory;

public interface BookService {
    public Book addBook(Book book);

    public Book getBookById(Long id);
    public Book getBookByIsbn(String isbn);
    public List<Book> getAllBooks();

    public Book updateBook(Book book);
    public void deleteBook(Long id);

    public List<Book> searchBooksByTitle(String title);

    public List<Book> getAvailableBooks();

    Book incrementCopies(Long bookId, int additionalCopies);
    Book decrementCopies(Long bookId, int copiesToRemove);

    Book updateAvailableCopies(Long bookId, int change);

    public Set<BorrowingHistory> getBorrowingHistory(Long bookId);

}
