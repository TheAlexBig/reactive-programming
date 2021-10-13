package com.alexbig.reactiveprogramming.service;

import com.alexbig.reactiveprogramming.entity.BookInfo;
import com.alexbig.reactiveprogramming.repository.BookInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BookInfoService {
    @Autowired private BookInfoRepository bookInfoRepository;
    @Autowired private ModelMapper modelMapper;

    public Flux<BookInfo> getBooks(){
        return bookInfoRepository.findAll();
    }

    public Mono<BookInfo> getBookInfoRepository(Integer bookId) {
        return bookInfoRepository.findById(bookId);
    }

    public Mono<BookInfo> createBookInfo(final BookInfo bookInfo){
        return bookInfoRepository.save(bookInfo);
    }

    public Mono<BookInfo> updateBookInfo(Integer bookId, final Mono<BookInfo> bookInfo){
        return bookInfoRepository.findById(bookId)
                .flatMap(b -> bookInfo.map(u ->{
                    modelMapper.map(u, b);
                    b.setId(bookId);
                    return b;
                }))
                .flatMap(book -> bookInfoRepository.save(book)).log();
    }

    public Mono<Void> deleteBookInfo(Integer bookId){
        return bookInfoRepository.deleteById(bookId);
    }
}
