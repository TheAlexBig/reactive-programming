package com.alexbig.reactiveprogramming.controller;

import com.alexbig.reactiveprogramming.entity.Book;
import com.alexbig.reactiveprogramming.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired private BookService bookService;

    @GetMapping("all")
    public Flux<Book> getAll(){
        return bookService.getAllBooks();
    }

    @GetMapping("{bookId}")
    public Mono<Book> findBookId(@PathVariable Integer bookId){
        return bookService.getBookById(bookId);
    }
}
