package com.healthcare.ingredient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredient_market")
public class IngredientMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientMarketId; //래퍼 클래스
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
