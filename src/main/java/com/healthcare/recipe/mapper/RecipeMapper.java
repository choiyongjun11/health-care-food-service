package com.healthcare.recipe.mapper;

import com.healthcare.recipe.dto.RecipeDto;
import com.healthcare.recipe.entity.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe RecipePostToRecipe(RecipeDto.Post requestBody);
    Recipe RecipePatchToRecipe(RecipeDto.Patch requestBody);
    RecipeDto.Response recipeToRecipeResponse(Recipe recipe);
}
