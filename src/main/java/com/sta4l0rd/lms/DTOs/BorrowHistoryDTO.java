package com.sta4l0rd.lms.DTOs;

@lombok.Data
public class BorrowHistoryDTO {
    private String BorrowHistoryid;
    
    private String bookid;
    private String bookTitle;
    private String bookAuthor;

    private String studentFirstName;
    private String studentLastName;
    private String studentid;
    
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private String bookStatus;
}
