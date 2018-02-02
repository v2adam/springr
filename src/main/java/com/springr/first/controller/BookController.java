package com.springr.first.controller;


import com.springr.first.domain.Book;
import com.springr.first.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my_api")
public class BookController {


    private BookService bookService;


    @Autowired
    public void setBookService(@Qualifier("bookServiceImpl") BookService bookService) {
        this.bookService = bookService;
    }

    // Returns a list
    @RequestMapping(value = "books", method = RequestMethod.GET)
    public Iterable<Book> getAllBooks() {
        return bookService.findAll();
    }


    // Returns a specific
    @RequestMapping(value = "books/{id}", method = RequestMethod.GET)
    public Book findOne(@PathVariable("id") Long id) {
        return bookService.findOne(id);
    }


    // Create a new
    @RequestMapping(value = "books", method = RequestMethod.POST)
    public Book createNew(@RequestBody Book inputBook) {
        return bookService.save(inputBook);
    }


    // Bulk update
    @RequestMapping(value = "books", method = RequestMethod.PUT)
    public Iterable<Book> update(@RequestBody Iterable<Book> books) {
        return bookService.save(books);
    }


    // Updates one
    @RequestMapping(value = "books/{id}", method = RequestMethod.PUT)
    public Book updateOne(@PathVariable("id") Long id, @RequestBody Book book) {
        return bookService.save(book);
    }


    // Delete all
    @RequestMapping(value = "books", method = RequestMethod.DELETE)
    public void deleteAll() {
        bookService.deleteAll();
    }


    // Delete a specific
    @RequestMapping(value = "books/{id}", method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable("id") Long id) {
        bookService.delete(id);
    }


}
