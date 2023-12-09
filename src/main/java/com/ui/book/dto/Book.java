package com.ui.book.dto;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    public enum BookType {
        HARDCOVER, SOFTCOVER, EBOOK
    }
    public long id;
    public String name;
    public String isbnNumber;
    @JsonFormat(pattern="dd/MM/yyyy")
    public Date publishDate;
    public Double price;
    public BookType bookType;

}
