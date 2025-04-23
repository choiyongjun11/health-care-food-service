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
    private Long recipeId;

    @OneToOne
    @JoinColumn(name="food_id")
    private Food food;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeStep> process = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Difficulty difficulty = Difficulty.DIFFICULTY_LEVEL_ONE;

    @Column(name = "total_cooking_time", nullable = true)
    private String totalCookingTime;

    public enum Difficulty {
        DIFFICULTY_LEVEL_ONE("쉬움"),
        DIFFICULTY_LEVEL_TWO("보통"),
        DIFFICULTY_LEVEL_THREE("어려움");

        @Getter
        private final String status;
        Difficulty(String status) {
            this.status = status;
        }
    }
}
