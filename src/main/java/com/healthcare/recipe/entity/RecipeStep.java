package com.healthcare.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable //엔티티 인식 x
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeStep {
    private int step;
    private String instruction;
    private String cooktime;
}
