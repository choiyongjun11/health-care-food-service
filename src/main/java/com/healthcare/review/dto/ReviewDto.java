package com.healthcare.review.dto;

import com.healthcare.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
public class ReviewDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotNull
        private long memberId; //리뷰 작성자
        @NotNull
        private long foodId; //리뷰대상 음식

        @NotSpace
        private String content;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long reviewId;
        @NotSpace
        private String content;

        public void setReviewId(long reviewId) {
            this.reviewId = reviewId;
        }


    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long reviewId;
        private String memberName; //작성한 리뷰에 사용자 이름 표시하여 식별
        private String content;
        private LocalDateTime reviewCreateDate;

    }

}