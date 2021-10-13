package com.alexbig.reactiveprogramming.service;

import com.alexbig.reactiveprogramming.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
        return allBooksInfo.flatMap(bookInfo -> {
            var reviews = reviewService.getReviewByBookId(bookInfo.getBookInfoId()).collectList();
            return reviews.map(listReviews -> new Book(bookInfo, listReviews));
        }).log();
    }
}
