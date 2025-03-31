package com.healthcare.food.mapper;

import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import com.healthcare.food.entity.FoodEffect;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food foodPostToFood(FoodDto.Post requestBody);
    Food foodPatchToFood(FoodDto.Patch requestBody);
    FoodDto.Response foodToFoodResponse(Food food);

    //foodeffect 객체의 리스트를 string 객체의 리스트로 변환이 필요합니다.
    default List<String> mapFoodEffects(List<FoodEffect> foodEffects) {
        if(foodEffects == null) return null;
        return foodEffects.stream()
                .map(FoodEffect::getEffectDescription)
                .collect(Collectors.toList());
    }


}
