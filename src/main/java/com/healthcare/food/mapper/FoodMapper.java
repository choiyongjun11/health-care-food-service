package com.healthcare.food.mapper;

import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import com.healthcare.ingredient.dto.IngredientDto;
import com.healthcare.recipe.dto.RecipeDto;
import com.healthcare.review.dto.ReviewDto;
import org.mapstruct.Mapper;
import java.util.Collections;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food foodPostToFood(FoodDto.Post requestBody);
    Food foodPatchToFood(FoodDto.Patch requestBody);

    // 음식 엔티티 -> DTO 변환
    default FoodDto.Response foodToFoodResponse(Food food) {
        //foodIngredient -> ingredientDto 변환
        List<IngredientDto.Response> ingredientDtos = Optional.ofNullable(food.getFoodIngredient())
                .orElse(Collections.emptyList())
                .stream()
                .map(ingredient -> {
                    if (ingredient.getIngredient() != null) {
                        return IngredientDto.Response.builder()
                                .ingredientId(ingredient.getIngredient().getIngredientId())
                                .ingredientName(ingredient.getIngredient().getIngredientName())
                                .ingredientType(ingredient.getIngredient().getIngredientType())
                                .ingredientOrigin(ingredient.getIngredient().getIngredientOrigin())
                                .expiryDate(ingredient.getIngredient().getExpiryDate())
                                .storageMethod(ingredient.getIngredient().getStorageMethod())
                                .build();
                    } else {
                        return null;
                    }
                })
                .filter(ingredientDto -> ingredientDto != null)
                .collect(Collectors.toList());

        //review -> reviewDto 변환
        List<ReviewDto.Response> reviewDtos = Optional.ofNullable(food.getReviews())
                .orElse(Collections.emptyList())
                .stream()
                .map(review -> ReviewDto.Response.builder()
                        .foodId(review.getFood().getFoodId())
                        .reviewId(review.getReviewId())
                        .memberId(review.getMember().getMemberId())
                        .memberName(review.getMember().getName())
                        .content(review.getContent())
                        .rating(review.getRating())
                        .reviewCreateDate(review.getReviewCreateDate())
                        .build())
                .collect(Collectors.toList());

        //recipe -> recipeDto 변환
        RecipeDto.Response recipeDto = null;
        if (food.getRecipe() != null) {
            recipeDto = RecipeDto.Response.builder()
                    .foodId(food.getFoodId())
                    .foodName(food.getFoodName())
                    .process(
                            Optional.ofNullable(food.getRecipe().getProcess())
                                    .orElse(Collections.emptyList())
                                    .stream()
                                    .map(step -> new RecipeDto.RecipeStepDto(
                                            step.getStep(),           // 몇 번째 스텝
                                            step.getInstruction(),    // 조리 지시
                                            step.getCooktime()         // 조리 시간
                                    ))
                                    .collect(Collectors.toList())
                    )
                    .difficulty(String.valueOf(food.getRecipe().getDifficulty()))
                    .totalCookingTime(food.getRecipe().getTotalCookingTime())
                    .build();
        }

        //ageGroup 매핑
        List<String> ageGroupNames = Optional.ofNullable(food.getAgeGroupFoods())
                .orElse(Collections.emptyList())
                .stream()
                .map(ageGroupFood -> ageGroupFood.getAgeGroup().getAgeGroupName())
                .collect(Collectors.toList());

        // FoodRecommend 매핑
        List<FoodDto.FoodRecommendDto> foodRecommendDtos = Optional.ofNullable(food.getFoodRecommends())
                .orElse(Collections.emptyList())
                .stream()
                .map(foodRecommend -> FoodDto.FoodRecommendDto.builder()
                                .foodRecommendId(foodRecommend.getFoodRecommendId())
                                .goalTypeCategory(foodRecommend.getGoalType().getGoalTypeCategory())
                                .goalTypeName(foodRecommend.getGoalType().getGoalTypeName())
                                .effect(foodRecommend.getEffect())
                                .reason(foodRecommend.getReason())
                                .build())
                .collect(Collectors.toList());

        return FoodDto.Response.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodCategory(food.getFoodCategory())
                .foodImageUrl(food.getFoodImageUrl())
                .foodDescription(food.getFoodDescription())
                .foodIngredients(ingredientDtos)
                .foodReviews(reviewDtos)
                .recipe(recipeDto)
                .ageGroupNames(ageGroupNames)
                .foodRecommends(foodRecommendDtos)
                .viewCount(Optional.ofNullable(food.getFoodViews()).orElse(Collections.emptyList()).size())
                .likeCount(Optional.ofNullable(food.getFoodLikes()).orElse(Collections.emptyList()).size())
                .foodCreateDate(food.getFoodCreateDate())
                .build();
    }


}
