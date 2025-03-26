package com.healthcare.food.entity;

import com.healthcare.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_like")
public class FoodLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodLikeId;

    //mapping 관계 설정 FoodLike (N) <-> Member (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //FK
    //mapping 관계 설정 FoodLike (N) <-> Food (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food; //FK

    @Column(nullable = false)
    private long likeCount;
    @Column(nullable = false)
    private LocalDateTime likeDate = LocalDateTime.now(); // 등록 날짜 자동 설정

}
