package com.healthcare.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="effect")
public class Effect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long effectId;
    private String effectName;

    //mapping 관계 설정 effect (1) <-> FoodEffect (N) 1:N 관계
    //mapping 관계 설정 effect (1) <-> TargetEffect (N) 1:N 관계


}
