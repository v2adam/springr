package com.springr.first.service;

import com.springr.first.domain.Book;

public interface BookService {


    Book save(Book book);


    Iterable<Book> save(Iterable<Book> book);


    Book findOne(Long id);


    Boolean exists(Long id);


    Iterable<Book> findAll();


    Iterable<Book> findAll(Iterable<Long> id);


    Long count();


    void delete(Long id);


    void delete(Book book);


    void delete(Iterable<Book> books);


    void deleteAll();

}
