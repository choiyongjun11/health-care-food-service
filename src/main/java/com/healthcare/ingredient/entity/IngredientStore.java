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
    private long ingredientId; //fk
    private String marketName;
    private String marketRegion;
    private long marketPrice;
    private String marketMap;
}
