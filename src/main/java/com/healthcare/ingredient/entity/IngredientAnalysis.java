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
    private Long ingredientAnalysisId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;  // FK

    @Column(nullable = false)
    private String nutrientName;

    @Column(nullable = false)
    private String amount;

    @Column(name = "daily_percentage") // 선택적 값
    private String dailyPercentage;

}
