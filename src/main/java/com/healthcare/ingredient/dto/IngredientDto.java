package com.healthcare.ingredient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthcare.validator.NotSpace;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

public class IngredientDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        @NotSpace
        private String ingredientName;
        @NotSpace
        private String ingredientType;
        @NotSpace
        private String ingredientOrigin;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate expiryDate; //유통기한 형식 필요
        @NotSpace
        private String storageMethod;

    }

    @Getter
    @Setter
    @NoArgsConstructor
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
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate expiryDate;  //2025-04-02
        @NotSpace
        private String storageMethod;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private long ingredientId;
        private String ingredientName;
        private String ingredientType;
        private String ingredientOrigin;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate expiryDate;
        private String storageMethod;

    }


}
