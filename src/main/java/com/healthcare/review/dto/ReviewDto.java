package com.healthcare.review.dto;

import com.healthcare.validator.NotSpace;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class ReviewDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        private String content;
        private int rating;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private long reviewId;
        @NotSpace //수정했을 때 공백이면 안된다. 공백일 경우 "다시 입력해주세요" 로 예외 발생
        private String content;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private long foodId;
        private long reviewId;
        private long memberId;
        private String memberName; //작성한 리뷰에 사용자 이름 표시하여 식별하기 위해 사용 ex) 홍길동
        private String content;
        private int rating;
        private LocalDate reviewCreateDate;

    }

}