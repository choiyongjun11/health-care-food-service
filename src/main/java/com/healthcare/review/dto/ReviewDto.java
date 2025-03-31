package com.healthcare.review.dto;

import com.healthcare.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
public class ReviewDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotNull
        private long memberId; //memberid 는 requestparam 으로(파라미터)로 전송
        @NotSpace
        private String content;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private long reviewId;
        @NotSpace //수정했을 때 공백이면 안된다.
        private String content;

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