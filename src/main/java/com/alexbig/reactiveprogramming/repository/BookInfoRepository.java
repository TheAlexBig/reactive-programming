package com.alexbig.reactiveprogramming.repository;

import com.alexbig.reactiveprogramming.entity.BookInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoRepository extends ReactiveCrudRepository<BookInfo, Integer> {
}
