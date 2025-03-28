package com.healthcare.recipe.entity;

import com.healthcare.food.entity.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId; //래퍼형

    //mapping 관계 설정 Recipe (N) <-> Food (1) N:1 관계
    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food; //FK

    @Column(nullable = false)
    private String recipeName;

    @Column(nullable = false)
    private String process;

    @Column(nullable = false)
    private String difficulty;

}
