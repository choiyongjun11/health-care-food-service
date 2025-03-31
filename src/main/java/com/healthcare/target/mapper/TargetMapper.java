package com.healthcare.target.mapper;

import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import com.healthcare.target.dto.TargetDto;
import com.healthcare.target.entity.AgeGroupFood;
import com.healthcare.target.entity.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TargetMapper {
    //entity <-> dto 로 변환하는 것.

    Target targetPostToTarget(TargetDto.Post targetDto);
    Target targetPatchToTarget(TargetDto.Patch targetDto);
   // @Mapping(source = "target.targetId", target = "targetId")
    //@Mapping(source = "target.ageGroup.ageGroupName", target = "ageGroupName")
    //@Mapping(source = "target.goalType.goalTypeCategory", target = "goalTypeCategory")
    //@Mapping(source = "target.goalType.goalTypeName", target = "goalTypeName")
    TargetDto.Response targetToResponseDto(Target target);


    //나이대별 음식 추천 리스트
    //food에 대한 응답 데이터를 순회 하여 가져와야 합니다.
    default List<FoodDto.Response> mapFoods(List<AgeGroupFood> ageGroupFoods) {
        return ageGroupFoods.stream().map(ageGroupFood -> {
            Food food = ageGroupFood.getFood();
            return new FoodDto.Response(
                    food.getFoodId(), // foodId
                    food.getFoodName(), // foodName
                    food.getFoodImageUrl(), // foodImageUrl
                    food.getFoodEffects().stream()  // foodEffects를 처리
                            .map(effect -> effect.getEffectDescription()) // effectDescription으로 변환
                            .collect(Collectors.toList()),  // List<String>으로 collect
                    food.getViewCount(),  // viewCount (Integer)
                    food.getFoodCreateDate() // foodCreateDate
            );
        }).collect(Collectors.toList());
    }

}
