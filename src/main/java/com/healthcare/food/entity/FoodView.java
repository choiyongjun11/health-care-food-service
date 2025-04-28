package com.healthcare.food.entity;

import com.healthcare.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_view")
public class FoodView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodViewId;

    @Column(nullable = false)
    private Long foodViewCount;

    //mapping 관계 설정 FoodView (N) <-> Member (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //FK

    //mapping 관계 설정 FoodView(N) <-> Food (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food; //FK

    public FoodView(Food food, Member member) {
        this.food = food;
        this.member = member;
        this.foodViewCount = 1L;

    }


}
