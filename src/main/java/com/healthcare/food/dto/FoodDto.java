package com.healthcare.food.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthcare.ingredient.dto.IngredientDto;
import com.healthcare.ingredient.entity.Ingredient;
import com.healthcare.review.dto.ReviewDto;
import com.healthcare.review.entity.Review;
import com.healthcare.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FoodDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotSpace
        private String foodName;
        @NotSpace
        private String foodCategory;
        @NotSpace
        private String foodImageUrl;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private long foodId;
        @NotSpace
        private String foodName;
        @NotSpace
        private String foodCategory;
        @NotSpace
        private String foodImageUrl;

    }

    @Getter
    @Setter
    @AllArgsConstructor //전체 생성자
    public static class Response {

        private long foodId;
        private String foodName; //김밥
        private String foodCategory; //밥
        private String foodImageUrl; //images/kimbap.jpg
        private Integer viewCount= 0; // 11
        private Integer likeCount= 0; //45
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate foodCreateDate; //2025-04-02


        //List 형태로 effect, reason 응답

    }


}
