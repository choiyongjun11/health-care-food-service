package com.healthcare.ingredient.dto;

import com.healthcare.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class IngredientDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotSpace
        private String ingredientName;
        @NotSpace
        private String ingredientType;
        @NotSpace
        private String ingredientOrigin;
        @NotSpace
        private LocalDate expiryDate; //유통기한 형식 필요
        @NotSpace
        private String storageMethod;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        @NotSpace
        private long ingredientId;
        @NotSpace
        private String ingredientName;
        @NotSpace
        private String ingredientType;
        @NotSpace
        private String ingredientOrigin;
        @NotSpace
        private LocalDate expiryDate; //2025-04-02
        @NotSpace
        private String storageMethod;

    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long ingredientId;
        private String ingredientName;
        private String ingredientType;
        private String ingredientOrigin;
        private LocalDate expiryDate;
        private String storageMethod;

    }


}
