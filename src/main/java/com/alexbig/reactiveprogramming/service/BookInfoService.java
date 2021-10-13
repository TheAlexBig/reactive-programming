package com.alexbig.reactiveprogramming.service;

import com.alexbig.reactiveprogramming.entity.BookInfo;
import com.alexbig.reactiveprogramming.repository.BookInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BookInfoService {
    @Autowired
    BookInfoRepository bookInfoRepository;

    public Flux<BookInfo> getBooks(){
        return bookInfoRepository.findAll();
    }

    public Mono<BookInfo> getBookInfoRepository(Integer id) {
        return bookInfoRepository.findById(id);
    }
}
