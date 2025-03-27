package com.healthcare.ingredient.mapper;

import com.healthcare.ingredient.dto.IngredientDto;
import com.healthcare.ingredient.entity.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    Ingredient ingredientPostToIngredient(IngredientDto.Post requestBody);
    Ingredient ingredientPatchToIngredient(IngredientDto.Patch requestBody);
    IngredientDto.Response ingredientToIngredientResponse(Ingredient ingredient);

}
