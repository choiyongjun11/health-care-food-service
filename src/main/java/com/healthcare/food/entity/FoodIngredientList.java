package com.healthcare.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_ingredient_list")
public class FoodIngredientList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodIngredientListId;
    //mapping 관계 설정 FoodIngredientList (N) <-> Food (1) N:1 관계
    private long foodId; //fk
    //mapping 관계 설정 FoodIngredientList (N) <-> Ingredient (1) N:1 관계
    private long ingredientId; //fk

}
