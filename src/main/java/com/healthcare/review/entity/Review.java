package com.healthcare.review.entity;
import com.healthcare.food.entity.Food;
import com.healthcare.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
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
    @JoinColumn(name = "member_id", nullable = false) // 리뷰를 작성하기 위해서는 회원에 대한 정보가 반드시 필수
    private Member member;

    //mapping 관계 설정 Review (N) <-> Food (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false) // 리뷰를 작성하기 위해서는 음식에 대한 정보가 반드시 필수
    private Food food;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int rating; // ⭐ 1~5점 사이의 별점 추가

    @Column(nullable = false, updatable = false)
    private LocalDate reviewCreateDate = LocalDate.now(); // 기본값은 오늘 날짜로 설정

}
