package com.healthcare.food.mapper;

import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food foodPostToFood(FoodDto.Post requestBody);
    Food foodPatchToFood(FoodDto.Patch requestBody);
    FoodDto.Response foodToFoodResponse(Food food);

}
