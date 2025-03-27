package com.healthcare.food.dto;

import com.healthcare.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

public class FoodDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotSpace
        private String foodName;
        //private String foodImageUrl; image 구현할 때 주석 풀기

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long foodId;
        @NotSpace
        private String foodName;
        //private String foodImageUrl; image 구현할 때 주석 풀기

        public void setFoodId(long foodId) {
            this.foodId = foodId;
        }

    }

    @Getter
    @AllArgsConstructor //전체 생성자
    public static class Response {
        private long foodId;
        private String foodName;
        //private String foodImageUrl; image 구현할 때 주석 풀기
        private int viewCount;
        private LocalDateTime foodCreateDate;

    }


}
