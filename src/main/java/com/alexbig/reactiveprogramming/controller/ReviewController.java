package com.alexbig.reactiveprogramming.controller;

import com.alexbig.reactiveprogramming.entity.Review;
import com.alexbig.reactiveprogramming.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("review")
public class ReviewController {
    @Autowired private ReviewService reviewService;

    @PostMapping
    public Mono<Review> createBookInfo(@RequestBody Mono<Review> reviewMono){
        return reviewMono.flatMap(review -> reviewService.createReview(review));
    }

    @GetMapping("all/book/{bookId}")
    public Flux<Review> getReviewsByBookId(@PathVariable Integer bookId){
        return reviewService.getReviewByBookId(bookId);
    }
}
