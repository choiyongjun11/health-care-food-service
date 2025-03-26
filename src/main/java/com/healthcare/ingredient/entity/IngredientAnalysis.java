package com.healthcare.ingredient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredient_analysis")
public class IngredientAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientAnalysisId; //래퍼 클래스

    //mapping 관계 설정 IngredientAnalysis (N) <-> Ingredient (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient; //FK

    @Column(nullable = false)
    private String nutrientName;
    @Column(nullable = false)
    private String amount;
    @Column(nullable = false)
    private String dailyPercentage;

}
