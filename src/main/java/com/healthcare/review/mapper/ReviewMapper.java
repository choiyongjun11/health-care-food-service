package com.healthcare.review.mapper;

import com.healthcare.review.dto.ReviewDto;
import com.healthcare.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review reviewPostToReview(ReviewDto.Post requestBody); //dto -> entity
    Review reviewPatchToReview(ReviewDto.Patch requestBody); //dto -> entity
    @Mapping(source = "member.name", target = "memberName")
    ReviewDto.Response reviewToResponse(Review review); //entity -> dto

}
