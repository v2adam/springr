package com.springr.first.service;


import com.springr.first.domain.Book;
import com.springr.first.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book save(Book book) {
        return bookRepository.save(book);
    }


    public Iterable<Book> save(Iterable<Book> book) {
        return bookRepository.save(book);
    }


    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }


    public Boolean exists(Long id) {
        return bookRepository.exists(id);
    }


    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Iterable<Book> findAll(Iterable<Long> id) {
        return bookRepository.findAll(id);
    }

    public Long count() {
        return bookRepository.count();
    }


    public void delete(Long id) {
        bookRepository.delete(id);
    }


    public void delete(Book book) {
        bookRepository.delete(book);
    }


    public void delete(Iterable<Book> books) {
        bookRepository.delete(books);
    }


    public void deleteAll() {
        bookRepository.deleteAll();
    }


}
