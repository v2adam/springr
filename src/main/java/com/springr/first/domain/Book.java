package com.springr.first.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private BigDecimal price;
    private Integer year;
    private String author;
    private String Isbn;


    public Book() {
    }


    public Book(String title, String description, BigDecimal price, Integer year, String author, String isbn) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.year = year;
        this.author = author;
        Isbn = isbn;
    }
}