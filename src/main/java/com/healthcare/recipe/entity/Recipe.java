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

    //mapping 관계설정 Recipe (1) <-> RecipeStep(N) 1:N 관계
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeStep> process = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty = Difficulty.DIFFICULTY_LEVEL_ONE;

    public enum Difficulty {
        DIFFICULTY_LEVEL_ONE("레벨1"),
        DIFFICULTY_LEVEL_TWO("레벨2"),
        DIFFICULTY_LEVEL_THREE("레벨3");

        private final String status;

        Difficulty(String status) {
            this.status = status;
        }

    }

}
