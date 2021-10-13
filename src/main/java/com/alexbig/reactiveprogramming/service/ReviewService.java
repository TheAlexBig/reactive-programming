package com.alexbig.reactiveprogramming.service;

import com.alexbig.reactiveprogramming.entity.Review;
import com.alexbig.reactiveprogramming.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public Flux<Review> getReviewByBookId(Integer id){
        return reviewRepository.findByBookInfoId(id);
    }
}
