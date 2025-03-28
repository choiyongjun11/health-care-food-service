package com.healthcare.ingredient.mapper;

import com.healthcare.ingredient.dto.IngredientDto;
import com.healthcare.ingredient.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    @Mapping(target = "ingredientId", ignore = true)
    @Mapping(target = "foodIngredient", ignore = true)
    @Mapping(target = "ingredientAnalyses", ignore = true)
    @Mapping(target = "ingredientMarkets", ignore = true)
    Ingredient ingredientPostToIngredient(IngredientDto.Post requestBody);
    @Mapping(target = "foodIngredient", ignore = true)
    @Mapping(target = "ingredientAnalyses", ignore = true)
    @Mapping(target = "ingredientMarkets", ignore = true)
    Ingredient ingredientPatchToIngredient(IngredientDto.Patch requestBody);
    IngredientDto.Response ingredientToIngredientResponse(Ingredient ingredient);

}
