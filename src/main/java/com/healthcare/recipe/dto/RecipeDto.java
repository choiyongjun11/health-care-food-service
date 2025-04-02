package com.healthcare.recipe.dto;

import com.healthcare.recipe.entity.RecipeStep;
import com.healthcare.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.List;

public class RecipeDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
       private List<RecipeStep> process;
       private String difficulty;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private Long recipeId;
        private List<RecipeStep> process;
        private String difficulty;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
      private String message;
      private Long recipeId;
      private List<RecipeStep> process;
      private String difficulty;
    }

}
