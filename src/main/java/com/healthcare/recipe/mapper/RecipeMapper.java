package com.healthcare.recipe.mapper;

import com.healthcare.recipe.dto.RecipeDto;
import com.healthcare.recipe.entity.Recipe;
import com.healthcare.recipe.entity.RecipeStep;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe RecipePostToRecipe(RecipeDto.Post requestBody);
    Recipe RecipePatchToRecipe(RecipeDto.Patch requestBody);

    default RecipeDto.Response recipeToRecipeResponse(Recipe recipe) {
        RecipeDto.Response response = new RecipeDto.Response();
        response.setFoodId(recipe.getFood().getFoodId());
        response.setFoodName(recipe.getFood().getFoodName());
        response.setDifficulty(recipe.getDifficulty().getStatus());
        response.setProcess(mapRecipeSteps(recipe.getProcess()));
        response.setTotalCookingTime(getTotalTime(recipe.getProcess()));
        return response;
    }

    default List<RecipeDto.RecipeStepDto> mapRecipeSteps(List<RecipeStep> steps) {
        return steps == null ? null : steps.stream().map(step ->
                new RecipeDto.RecipeStepDto(
                        step.getStep(),
                        step.getInstruction(),
                        step.getCooktime() + "분"
                )
        ).collect(Collectors.toList());
    }

    default String getTotalTime(List<RecipeStep> steps) {
        int total = steps == null ? 0 : steps.stream().mapToInt(RecipeStep::getCooktime).sum();
        return total + "분";
    }


}
