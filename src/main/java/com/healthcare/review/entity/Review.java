package com.healthcare.review.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    //mapping 관계 설정 Review (1) <-> Member (N) 1:N 관계
    private long memberId; //fk
    //mapping 관계 설정 Review (1) <-> Food (N) 1:N 관계
    private long foodId; //fk

    private String content;
    private LocalDateTime reviewCreateDate = LocalDateTime.now(); // 등록 날짜 자동 설정
}
