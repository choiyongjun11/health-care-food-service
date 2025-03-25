package com.healthcare.review.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    private int reviewId;
    private String content;
    private LocalDateTime reviewCreateDate = LocalDateTime.now(); // 등록 날짜 자동 설정
}
