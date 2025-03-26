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
    private long foodViewId;

    //mapping 관계 설정 FoodView (N) <-> Member (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //FK

    //mapping 관계 설정 FoodView(N) <-> FoodIngredientList (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "food_ingredient_list_id")
    private FoodIngredientList foodIngredientList; //FK

    @Column(nullable = false)
    private LocalDateTime viewDate = LocalDateTime.now(); // 등록 날짜 자동 설정

}
