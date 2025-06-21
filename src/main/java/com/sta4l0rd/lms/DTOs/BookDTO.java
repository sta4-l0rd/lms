package com.sta4l0rd.lms.DTOs;

@lombok.Data
public class BookDTO {
    private String idString;
    private String title;
    private String author;
    private String isbn;
    private String totalCopiesString;
    private String availableCopiesString;
}
