package com.healthcare.food.controller;

import com.healthcare.dto.SingleResponseDto;
import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import com.healthcare.food.mapper.FoodMapper;
import com.healthcare.food.repository.FoodRepository;
import com.healthcare.food.service.FoodService;
import com.healthcare.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/foods")
public class FoodController {
    private final static String FOOD_DEFAULT_URL = "/foods";
    private final FoodService foodService;
    private final FoodMapper mapper;

    public FoodController(FoodService foodService, FoodRepository foodRepository, FoodMapper mapper) {
        this.foodService = foodService;
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
    public ResponseEntity getFood(@PathVariable("food-id") long foodId) {
        Food food = foodService.findFood(foodId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.foodToFoodResponse(food)), HttpStatus.OK);
    }

    @DeleteMapping("/{food-id}")
    public ResponseEntity deleteFood(@PathVariable("food-id") long foodId) {
        foodService.deleteFood(foodId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
