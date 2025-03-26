package com.healthcare.review.entity;

import com.healthcare.food.entity.Food;
import com.healthcare.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "member_id",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    //mapping 관계 설정 Review (1) <-> Food (N) 1:N 관계
    @OneToMany(mappedBy = "food_id", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Food> foods = new ArrayList<>();

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDateTime reviewCreateDate = LocalDateTime.now(); // 등록 날짜 자동 설정
}
