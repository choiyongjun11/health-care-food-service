package com.healthcare.food.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.healthcare.ingredient.dto.IngredientDto;
import com.healthcare.recipe.dto.RecipeDto;
import com.healthcare.review.dto.ReviewDto;
import com.healthcare.validator.NotSpace;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


public class FoodDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FoodRecommendDto {
        private Long foodRecommendId;
        private String goalTypeCategory; //목표 카테고리
        private String goalTypeName; //목표 이름
        private String effect; //음심의 영향
        private String reason; //이유

    }


    @Getter
    @NoArgsConstructor
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
    @NoArgsConstructor
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
    @NoArgsConstructor
    @AllArgsConstructor //전체 생성자
    @Builder
    public static class Response {
        private long foodId;
        private String foodName; //김밥
        private String foodCategory; //밥
        private String foodImageUrl; //images/kimbap.jpg

        private List<IngredientDto.Response> foodIngredients; //음식 식재료 정보

        private List<ReviewDto.Response> foodReviews; //리뷰 정보

        private RecipeDto.Response recipe; //레시피

        private List<String> ageGroupNames;

        private List<FoodRecommendDto> foodRecommends;

        private Integer viewCount; // 11
        private Integer likeCount; //45
        private Boolean liked; //좋아요 눌렀는지 여부
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate foodCreateDate; //2025-04-02

        //List 형태로 effect, reason 응답

        public void updateLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public void updateViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

    }


}
