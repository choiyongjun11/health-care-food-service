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
    private long ingredientAnalysisId;
    //mapping 관계 설정 IngredientAnalysis (N) <-> Ingredient (1) N:1 관계
    private long ingredientId; // fk
    private String nutrientName;
    private String amount;
    private String dailyPercentage;

}
