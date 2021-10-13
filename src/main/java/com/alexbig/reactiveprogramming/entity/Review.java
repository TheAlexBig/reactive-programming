package com.alexbig.reactiveprogramming.entity;

import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Review {
    @Id
    private Integer reviewId;
    private Integer bookInfoId;
    private Double ratings;
    private String comments;
}
