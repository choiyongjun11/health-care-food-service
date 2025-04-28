package com.healthcare.recipe.dto;

import com.healthcare.recipe.entity.Recipe;
import lombok.*;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class RecipeDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecipeStepDto {
        @Min(1)
        private int step;
        @NotNull
        private String instruction;

        private String cooktime;

        public RecipeStepDto(int step, String instruction, int cooktime) {
            this.step = step;
            this.instruction = instruction;
            this.cooktime = String.valueOf(cooktime);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private List<RecipeStepDto> process;
        private String difficulty;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private Long recipeId;
        private List<RecipeStepDto> process;
        private String difficulty;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long foodId;
        private String foodName;
        private List<RecipeStepDto> process;
        private String difficulty;
        private String totalCookingTime;


    }

}
