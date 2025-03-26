package com.healthcare.ingredient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ingredientId;
    @Column(nullable = false)
    private String ingredientName;
    @Column(nullable = false)
    private String ingredientType;
    @Column(nullable = false)
    private String ingredientOrigin;
    @Column(nullable = false)
    private LocalDateTime expiryDate = LocalDateTime.now();
    @Column(nullable = false)
    private String storageMethod;

    //mapping 관계 설정 Ingredient (1) <-> FoodIngredientList (N) 1:N 관계
    //mapping 관계 설정 Ingredient (1) <-> IngredientAnalysis (N) 1:N 관계
    //mapping 관계 설정 Ingredient (1) <-> IngredientStore (N) 1:N 관계


}
