package com.healthcare.food.entity;

import com.healthcare.ingredient.entity.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_ingredient")
public class FoodIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodIngredientId; //기본형 long , 래퍼 Long

    //mapping 관계 설정 FoodIngredient (N) <-> Food (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food; //FK

    //mapping 관계 설정 FoodIngredient (N) <-> Ingredient (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient; //FK

}
