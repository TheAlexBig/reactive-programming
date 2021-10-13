package com.alexbig.reactiveprogramming.controller;


import com.alexbig.reactiveprogramming.entity.BookInfo;
import com.alexbig.reactiveprogramming.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("book")
public class BookInfoController {
    @Autowired private BookInfoService bookInfoService;

    @PostMapping
    public Mono<BookInfo> createBookInfo(@RequestBody Mono<BookInfo> bookInfoMono){
        return bookInfoMono.flatMap(bookInfo -> bookInfoService.createBookInfo(bookInfo));
    }

    @GetMapping("{bookId}/info")
    public Mono<BookInfo> findBook(@PathVariable Integer bookId){
        return bookInfoService.getBookInfoRepository(bookId);
    }

    @PutMapping("{bookId}")
    public Mono<BookInfo> updateBook(@PathVariable Integer bookId, @RequestBody Mono<BookInfo> bookInfoMono){
        return bookInfoService.updateBookInfo(bookId, bookInfoMono);
    }

    @DeleteMapping("{bookId}")
    public Mono<Void> deleteProduct(@PathVariable Integer bookId){
        return bookInfoService.deleteBookInfo(bookId);
    }
}
