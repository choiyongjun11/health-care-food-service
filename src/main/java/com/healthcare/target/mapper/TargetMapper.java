package com.healthcare.target.mapper;

import com.healthcare.target.dto.TargetDto;
import com.healthcare.target.entity.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TargetMapper {
    //entity <-> dto 로 변환하는 것.

    @Mapping(target = "goalType.goalTypeCategory", source = "goalTypeCategory")
    @Mapping(target = "goalType.goalTypeName", source = "goalTypeName")
    @Mapping(target = "ageGroup.ageGroupName", source = "ageGroupName")
    @Mapping(target = "targetStatus", ignore = true)
    Target targetPostToTarget(TargetDto.Post targetDto);

    @Mapping(target = "goalType.goalTypeCategory", source = "goalTypeCategory")
    @Mapping(target = "goalType.goalTypeName", source = "goalTypeName")
    @Mapping(target = "ageGroup.ageGroupName", source = "ageGroupName")
    @Mapping(target = "targetStatus", ignore = true)
    Target targetPatchToTarget(TargetDto.Patch targetDto);

    TargetDto.Response targetToResponse(Target target);

}
