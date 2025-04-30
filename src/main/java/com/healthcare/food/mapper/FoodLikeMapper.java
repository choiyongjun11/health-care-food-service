package com.healthcare.food.mapper;

import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FoodLikeMapper {

    default FoodDto.LikeResponse foodToLikeResponse(Food food, int likeCount, boolean liked) {
        return FoodDto.LikeResponse.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodCategory(food.getFoodCategory())
                .foodImageUrl(food.getFoodImageUrl())
                .foodDescription(food.getFoodDescription())
                .likeCount(likeCount)
                .liked(liked)
                .build();
    }


}
