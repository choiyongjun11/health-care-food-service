package com.healthcare.recipe.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeId;
    //mapping 관계 설정 Recipe (N) <-> Food (1) N:1 관계
    private long foodId; // fk
    @Column(nullable = false)
    private String process;
    @Column(nullable = false)
    private String difficulty;



}
