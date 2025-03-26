package com.healthcare.ingredient.entity;

import com.healthcare.food.entity.FoodIngredientList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @OneToMany(mappedBy = "food_ingredient_list_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodIngredientList> foodIngredientLists = new ArrayList<>();

    //mapping 관계 설정 Ingredient (1) <-> IngredientAnalysis (N) 1:N 관계
    @OneToMany(mappedBy = "ingredient_analysis_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientAnalysis> ingredientAnalyses = new ArrayList<>();

    //mapping 관계 설정 Ingredient (1) <-> IngredientStore (N) 1:N 관계
    @OneToMany(mappedBy = "ingredient_store_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientStore> ingredientStores = new ArrayList<>();

}
