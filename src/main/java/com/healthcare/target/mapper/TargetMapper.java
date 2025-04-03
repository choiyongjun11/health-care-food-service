package com.healthcare.target.mapper;

import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import com.healthcare.target.dto.TargetDto;
import com.healthcare.target.entity.AgeGroupFood;
import com.healthcare.target.entity.Target;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TargetMapper {
    //entity <-> dto 로 변환하는 것.

    Target targetPostToTarget(TargetDto.Post targetDto);
    Target targetPatchToTarget(TargetDto.Patch targetDto);
    TargetDto.Response targetToResponse(Target target);

}
