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
public class BookInfo implements Persistable<Integer> {
    @Id
    private Integer id;
    private String title;
    private String author;
    private String ISBN;

    @Transient
    private boolean newBookInfo;

    @Override
    @Transient
    public boolean isNew() {
        return this.newBookInfo || id == null;
    }

    public BookInfo setAsNew(){
        this.newBookInfo = true;
        return this;
    }
}
