package com.healthcare.ingredient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredient_store")
public class IngredientStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ingredientStoreId;
    //mapping 관계 설정 IngredientStore (N) <-> Ingredient (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient; //FK

    @Column(nullable = false)
    private String marketName;
    @Column(nullable = false)
    private String marketRegion;
    @Column(nullable = false)
    private long marketPrice;
    @Column(nullable = false)
    private String marketMap;
}
