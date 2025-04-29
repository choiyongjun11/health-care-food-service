package com.healthcare.member.mapper;

import com.healthcare.food.entity.FoodLike;
import com.healthcare.member.dto.MemberDto;
import com.healthcare.member.entity.Member;
import com.healthcare.member.entity.MemberTarget;
import com.healthcare.review.entity.Review;
import com.healthcare.target.entity.Target;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post requestBody);
    Member memberPatchToMember(MemberDto.Patch requestBody);
    MemberDto.Response memberToMemberResponse(Member member);

}
