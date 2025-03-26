package com.healthcare.food.entity;

import com.healthcare.target.entity.TargetEffect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="effect")
public class Effect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long effectId;

    @Column(nullable = false)
    private String effectName;

    //mapping 관계 설정 effect (1) <-> FoodEffect (N) 1:N 관계
    @OneToMany(mappedBy = "effect", cascade = CascadeType.PERSIST)
    private List<FoodEffect> foodEffects = new ArrayList<>();

    //mapping 관계 설정 effect (1) <-> TargetEffect (N) 1:N 관계
    @OneToMany(mappedBy = "effect", cascade = CascadeType.PERSIST)
    private List<TargetEffect> targetEffects = new ArrayList<>();

}
