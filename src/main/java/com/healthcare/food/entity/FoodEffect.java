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
    private Long foodEffectId;

    @Column(nullable = false)
    private String effectDescription; //효과 설명

    //mapping 관계 설정 FoodEffect (N) <-> Food (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food; //FK

    //mapping 관계 설정 FoodEffect (N) <-> Effect (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "effect_id")
    private Effect effect; //FK
}
