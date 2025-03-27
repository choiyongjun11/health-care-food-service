package com.healthcare.food.mapper;

import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food foodPostToFood(FoodDto.Post requestBody);
    Food foodPatchToFood(FoodDto.Patch requestBody);
    FoodDto.Response foodToFoodResponse(Food food);


}
