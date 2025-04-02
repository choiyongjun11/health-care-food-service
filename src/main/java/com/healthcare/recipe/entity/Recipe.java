package com.healthcare.recipe.entity;

import com.healthcare.food.entity.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId; //래퍼형

    //mapping 관계 설정 Recipe (1) <-> Food (1) 1:1 관계
    @OneToOne
    @JoinColumn(name="food_id")
    private Food food; //FK

    @ElementCollection
    @CollectionTable(name = "recipe_process", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<RecipeStep> process = new ArrayList<>();

    private Difficulty difficulty = Difficulty.DIFFICULTY_LEVEL_ONE; //default : 초급
    public enum Difficulty {
        DIFFICULTY_LEVEL_ONE("초급"),
        DIFFICULTY_LEVEL_TWO ("중급"),
        DIFFICULTY_LEVEL_THREE("상급");

        @Getter
        private String status;
        Difficulty(String status) {
            this.status = status;
        }

    }

}
