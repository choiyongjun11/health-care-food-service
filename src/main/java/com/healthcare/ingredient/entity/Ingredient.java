package com.healthcare.ingredient.entity;

import com.healthcare.food.entity.FoodIngredient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId; //래퍼형
    @Column(nullable = false)
    private String ingredientName;  //감자
    @Column(nullable = false)
    private String ingredientType; //식량작물
    @Column(nullable = false)
    private String ingredientOrigin; //국내산
    @Column(nullable = false)
    private LocalDate expiryDate; //2025-04-02
    @Column(nullable = false)
    private String storageMethod; //상온보관

    //mapping 관계 설정 Ingredient (1) <-> FoodIngredient (N) 1:N 관계
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodIngredient> foodIngredient = new ArrayList<>();

    //mapping 관계 설정 Ingredient (1) <-> IngredientAnalysis (N) 1:N 관계
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientAnalysis> ingredientAnalyses = new ArrayList<>();

    //mapping 관계 설정 Ingredient (1) <-> IngredientMarket (N) 1:N 관계
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientMarket> ingredientMarkets = new ArrayList<>();

}
