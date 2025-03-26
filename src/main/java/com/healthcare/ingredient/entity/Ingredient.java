package com.healthcare.ingredient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String ingredientName;
    private String ingredientType;
    private String ingredientOrigin;
    private Date expiryDate;
    private String storageMethod;

    //mapping 관계 설정 Ingredient (1) <-> FoodIngredientList (N) 1:N 관계
    //mapping 관계 설정 Ingredient (1) <-> IngredientAnalysis (N) 1:N 관계
    //mapping 관계 설정 Ingredient (1) <-> IngredientStore (N) 1:N 관계


}
