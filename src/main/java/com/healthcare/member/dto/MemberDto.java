package com.healthcare.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.FoodLike;
import com.healthcare.food.entity.FoodView;
import com.healthcare.member.entity.MemberTarget;
import com.healthcare.review.dto.ReviewDto;
import com.healthcare.review.entity.Review;
import com.healthcare.target.dto.TargetDto;
import com.healthcare.validator.NotSpace;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotSpace
        @Email
        private String email;

        @NotSpace
        private String password;

        @NotSpace
        private String name;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthday;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 11자리로 구성되어 있으며 '-' 형태로 되어야 합니다.")
        private String phone;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;

        @NotSpace
        private String name;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthday;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 11자리로 구성되어 있으며 '-' 형태로 되어야 합니다.")
        private String phone;

        public void setMemberId(long memberId)
        {
            this.memberId = memberId;
        }

    }

    @Getter
    @AllArgsConstructor
    public static class Response {

        private long memberId;
        private String email;
        private String name;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthday;
        private String phone;
    }

    @Getter
    @AllArgsConstructor
    public static class MemberActivityResponse {
        private List<FoodDto.LikeResponse> likedFoods;
        private List<ReviewDto.Response> writtenReviews;
        private List<TargetDto.Response> healthTargets;

    }


}
