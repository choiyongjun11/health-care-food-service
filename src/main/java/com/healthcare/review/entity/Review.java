package com.healthcare.review.entity;

import com.healthcare.food.entity.Food;
import com.healthcare.member.entity.Member;
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

    //mapping 관계 설정 Review (N) <-> Member (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    //mapping 관계 설정 Review (N) <-> Food (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime reviewCreateDate = LocalDateTime.now(); // 등록 날짜 자동 설정
}
