package com.healthcare.target.mapper;

import com.healthcare.target.dto.TargetDto;
import com.healthcare.target.entity.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TargetMapper {
    //entity <-> dto 로 변환하는 것.
    Target targetPostToTarget(TargetDto.Post targetDto);
    Target targetPatchToTarget(TargetDto.Patch targetDto);
    TargetDto.Response targetToResponse(Target target);
}
