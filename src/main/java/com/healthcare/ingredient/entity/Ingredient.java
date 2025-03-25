package com.healthcare.ingredient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ingredient")
public class Ingredient {
    private long ingredientId;
    private String ingredientName;
    private String ingredientType;
    private String ingredientOrigin;
    private Date expiryDate;
    private String storageMethod;



}
