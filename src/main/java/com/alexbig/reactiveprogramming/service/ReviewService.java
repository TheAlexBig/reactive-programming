package com.alexbig.reactiveprogramming.service;

import com.alexbig.reactiveprogramming.entity.Review;
import com.alexbig.reactiveprogramming.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ReviewService {
    @Autowired private ReviewRepository reviewRepository;

    public Mono<Review> createReview(Review review){
        return reviewRepository.save(review);
    }

    public Flux<Review> getReviewByBookId(Integer id){
        return reviewRepository.findByBookInfoId(id).log();
    }
}
