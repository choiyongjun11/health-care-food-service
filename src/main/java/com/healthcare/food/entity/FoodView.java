package com.healthcare.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    private long memberId; //fk
    //mapping 관계 설정 FoodView(N) <-> FoodIngredientList (1) N:1 관계
    private long foodId; //fk
    private Date viewDate;

}
