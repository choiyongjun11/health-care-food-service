package com.healthcare.recipe.dto;

import com.healthcare.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import java.util.List;

public class RecipeDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecipeStepDto {
        private int step;
        private String instruction;
        private String cooktime;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private List<RecipeStepDto> process;
        private Recipe.Difficulty difficulty;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private Long recipeId;
        private List<RecipeStepDto> process;
        private Recipe.Difficulty difficulty;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private Long recipeId;
        private String foodName;
        private List<RecipeStepDto> process;
        private Recipe.Difficulty difficulty;
    }

}
