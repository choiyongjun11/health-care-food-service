package com.healthcare.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_effect")
public class FoodEffect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodEffectId;

    //mapping 관계 설정 FoodEffect (N) <-> Food (1) N:1 관계
    private long foodId;  //fk
    //mapping 관계 설정 FoodEffect (N) <-> Effect (1) N:1 관계
    private long effectId; //fk

}
