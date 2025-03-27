package com.healthcare.recipe.dto;

import com.healthcare.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class RecipeDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotSpace
        private String recipeName;
        @NotSpace
        private String process;
        @NotSpace
        private String difficulty;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long recipeId;
        @NotSpace
        private String recipeName;
        @NotSpace
        private String process;
        @NotSpace
        private String difficulty;

        public void setRecipeId(long recipeId) {
            this.recipeId = recipeId;
        }

    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long recipeId;
        private String recipeName;
        private String process;
        private String difficulty;
    }

}
