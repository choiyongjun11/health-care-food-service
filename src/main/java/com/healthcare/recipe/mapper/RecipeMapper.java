package com.healthcare.recipe.mapper;

import com.healthcare.recipe.dto.RecipeDto;
import com.healthcare.recipe.entity.Recipe;
import com.healthcare.recipe.entity.RecipeStep;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    Recipe RecipePostToRecipe(RecipeDto.Post requestBody);
    Recipe RecipePatchToRecipe(RecipeDto.Patch requestBody);
    @Mapping(source = "food.foodName", target= "foodName")
    RecipeDto.Response recipeToRecipeResponse(Recipe recipe);

}
