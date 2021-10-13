package com.alexbig.reactiveprogramming.service;

import com.alexbig.reactiveprogramming.entity.BookInfo;
import com.alexbig.reactiveprogramming.entity.Review;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceTest {
    public static final Integer ID1 = 1;
    public static final Integer ID2 = 2;
    public static final Integer ID3 = 3;
    @Autowired BookService bookService;
    @MockBean BookInfoService mockBookInfoService;
    @MockBean ReviewService mockReviewService;

    public static Flux<BookInfo> listOfBooks;
    public static Flux<Review> listOfReviews1;
    public static Flux<Review> listOfReviews2;
    public static Flux<Review> listOfReviews3;


    @BeforeAll
    static void setBookService(){
        var bookInfo_1 = BookInfo.builder()
                .bookInfoId(ID1)
                .author("Author One")
                .ISBN("12345678")
                .title("Book One")
                .build();
        var bookInfo_2 = BookInfo.builder()
                .bookInfoId(ID2)
                .author("Author Two")
                .ISBN("22345678")
                .title("Book Two")
                .build();
        var bookInfo_3 = BookInfo.builder()
                .bookInfoId(ID3)
                .author("Author Three")
                .ISBN("32345678")
                .title("Book Three")
                .build();
        listOfBooks = Flux.just(bookInfo_1,bookInfo_2,bookInfo_3);


        var review_1 = Review.builder()
                .reviewId(1)
                .bookInfoId(ID1)
                .ratings(5.0)
                .comments("Great!")
                .build();
        var review_2 = Review.builder()
                .reviewId(2)
                .bookInfoId(ID1)
                .ratings(3.0)
                .comments("Good!")
                .build();
        var review_3 = Review.builder()
                .reviewId(3)
                .bookInfoId(ID2)
                .ratings(1.0)
                .comments("OMEGALUL")
                .build();
        var review_4 = Review.builder()
                .reviewId(4)
                .bookInfoId(ID3)
                .ratings(2.0)
                .comments("Could be better")
                .build();
        listOfReviews1 = Flux.just(review_1, review_2);
        listOfReviews2 = Flux.just(review_3);
        listOfReviews3 = Flux.just(review_4);

    }

   @Test
    void getAllBooks() {
        when(mockBookInfoService.getBooks()).thenReturn(listOfBooks);
        when(mockReviewService.getReviewByBookId(ID1)).thenReturn(listOfReviews1);
        when(mockReviewService.getReviewByBookId(ID2)).thenReturn(listOfReviews2);
        when(mockReviewService.getReviewByBookId(ID3)).thenReturn(listOfReviews3);
        var books = bookService.getAllBooks();
        StepVerifier.create(books)
                .expectNextCount(3)
                .thenConsumeWhile(book -> {
                    assertTrue(book.getReviews().size()>0);
                    return true;
                })
                .verifyComplete();
    }


}