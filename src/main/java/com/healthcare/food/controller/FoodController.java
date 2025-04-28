package com.healthcare.food.controller;

import com.healthcare.ingredient.entity.Ingredient;
import com.healthcare.ingredient.service.IngredientService;
import com.healthcare.response.MultiResponseDto;
import com.healthcare.response.SingleResponseDto;
import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import com.healthcare.food.mapper.FoodMapper;
import com.healthcare.food.repository.FoodRepository;
import com.healthcare.food.service.FoodService;
import com.healthcare.review.service.ReviewService;
import com.healthcare.utils.UriCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/foods")
public class FoodController {
    private final static String FOOD_DEFAULT_URL = "/foods";
    private final FoodService foodService;
    private final IngredientService ingredientService;
    private final ReviewService reviewService;
    private final FoodMapper mapper;

    public FoodController(FoodService foodService, IngredientService ingredientService, ReviewService reviewService, FoodMapper mapper) {
        this.foodService = foodService;
        this.ingredientService = ingredientService;
        this.reviewService = reviewService;
        this.mapper = mapper;
    }



    @PostMapping
    public ResponseEntity postFood(@RequestBody FoodDto.Post requestBody) {
        Food food = mapper.foodPostToFood(requestBody);
        Food createdFood = foodService.createFood(food);
        URI location = UriCreator.createUri(FOOD_DEFAULT_URL, createdFood.getFoodId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{food-id}")
    public ResponseEntity patchFood(@PathVariable("food-id") long foodId, @RequestBody FoodDto.Patch requestBody) {
      requestBody.setFoodId(foodId);
      Food food = foodService.updateFood(mapper.foodPatchToFood(requestBody));
      return new ResponseEntity<>(new SingleResponseDto<>(mapper.foodToFoodResponse(food)), HttpStatus.OK);

    }

    @GetMapping("/{food-id}")
    public ResponseEntity<SingleResponseDto<FoodDto.Response>> getFood(@PathVariable("food-id") long foodId) {

        Food food = foodService.findFood(foodId);


        // 2. 좋아요 & 조회수 조회
        int likeCount = foodService.getFoodLikeCount(food);
        int viewCount = foodService.getFoodViewCount(food);

        FoodDto.Response response = mapper.foodToFoodResponse(food);
        response.setLikeCount(likeCount);
        response.setViewCount(viewCount);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getFoods(@RequestParam int page, @RequestParam int size) {
        Page<Food> foodPage = foodService.findFoods(page,size);

        List<FoodDto.Response> responses = foodPage.getContent().stream()
                .map(food -> {
                    FoodDto.Response response = mapper.foodToFoodResponse(food);

                    int likeCount = foodService.getFoodLikeCount(food);
                    int viewCount = foodService.getFoodViewCount(food);
                    response.updateLikeCount(likeCount);
                    response.updateViewCount(viewCount);

                    return response;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(new MultiResponseDto<>(responses, foodPage));

    }

    @DeleteMapping("/{food-id}")
    public ResponseEntity deleteFood(@PathVariable("food-id") long foodId) {
        foodService.deleteFood(foodId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
