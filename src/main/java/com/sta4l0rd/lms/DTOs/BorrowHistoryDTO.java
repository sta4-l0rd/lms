package com.sta4l0rd.lms.DTOs;

@lombok.Data
public class BorrowHistoryDTO {
    private String BorrowHistoryIdString;
    
    private String bookIdString;
    private String bookTitleString;
    private String bookAuthorString;

    private String studentFirstNameString;
    private String studentLastNameString;
    private String studentIdString;
    
    private String borrowDateString;
    private String dueDateString;
    private String returnDateString;
    private String bookStatusString;
}
