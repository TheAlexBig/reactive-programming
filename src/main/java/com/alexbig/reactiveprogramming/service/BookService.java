package com.alexbig.reactiveprogramming.service;

import com.alexbig.reactiveprogramming.entity.Book;
import com.alexbig.reactiveprogramming.entity.BookInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BookService {
    final BookInfoService bookInfoService;
    final ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getAllBooks(){
        var allBooksInfo = bookInfoService.getBooks();
        return allBooksInfo.flatMap(this::getMonoBook).log();
    }

    public Mono<Book> getBookById(Integer id){
        var getOneBook = bookInfoService.getBookInfoRepository(id);
        return getOneBook.flatMap(this::getMonoBook).log();
    }

    private Mono<Book> getMonoBook(BookInfo bookInfo) {
        var reviews = reviewService.getReviewByBookId(bookInfo.getId()).collectList();
        return reviews.map(listReviews -> new Book(bookInfo, listReviews));
    }
}
