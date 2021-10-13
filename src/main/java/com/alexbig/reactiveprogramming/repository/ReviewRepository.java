package com.alexbig.reactiveprogramming.repository;

import com.alexbig.reactiveprogramming.entity.Review;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReviewRepository extends ReactiveCrudRepository<Review, Integer> {
    Flux<Review> findByBookInfoId(Integer bookId);
}
