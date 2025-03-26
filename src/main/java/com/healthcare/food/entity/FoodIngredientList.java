package com.healthcare.food.entity;

import com.healthcare.ingredient.entity.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

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
    @ManyToOne
    @JoinColumn(name = "food_ingredient_list_id")
    private FoodIngredientList foodIngredientList; //FK
    //mapping 관계 설정 FoodIngredientList (N) <-> Ingredient (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient; //FK


}
