package com.alexbig.reactiveprogramming.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Review implements Persistable<Integer> {
    @Id
    private Integer id;
    private Integer bookInfoId;
    private Double ratings;
    private String comments;

    @Transient
    private boolean newReview;

    @Override
    public boolean isNew() {
        return this.newReview || this.id == null;
    }

    public Review setAsNew(){
        this.newReview = true;
        return this;
    }
}
