package com.alexbig.reactiveprogramming.entity;

import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookInfo {
    @Id
    private Integer bookInfoId;
    private String title;
    private String author;
    private String ISBN;
}
