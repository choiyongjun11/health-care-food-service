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

        private List<IngredientDto.Response> foodIngredients; //음식에 들어가는 식재료 정보
        private List<ReviewDto.Response> foodReviews; //리뷰 정보
        private RecipeDto.Response recipe; //레시피 정보
        private List<String> ageGroupNames; //타겟 연령대
        private List<FoodRecommendDto> foodRecommends; //음식 추천 리스트

        private Integer viewCount; // 조회수
        private Integer likeCount; //좋아요 수
        private Boolean liked; //좋아요 눌렀는지 여부
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate foodCreateDate; //2025-04-02 등록 날짜

        //List 형태로 effect, reason 응답

        //좋아요 수 업데이트
        public void updateLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }
        //조회수 업데이트
        public void updateViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        //좋아요 여부 업데이트
        public void updateLiked(boolean liked) {
            this.liked = liked;
        }

    }

    //좋아요 결과 응답 DTO
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LikeResponse {
        private long foodId;
        private int likeCount;
        private boolean liked;
    }


}
