package com.healthcare.food.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.food.entity.Food;
import com.healthcare.food.repository.FoodRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {
    //create, find, update, delete
    private FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food createFood(Food food) {
        verifyExistFood(food.getFoodName());
        Food saveFood = foodRepository.save(food);
        return saveFood;
    }


    public Food findFood(long foodId) {
        return findVerifiedFood(foodId);

    }

    public Page<Food> findFoods (int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return foodRepository.findAll(pageable);
    }

    public Food updateFood(Food food) {
        Food findFood = findVerifiedFood(food.getFoodId());
        Optional.ofNullable(food.getFoodName()).ifPresent(foodName -> findFood.setFoodName(foodName));
        return foodRepository.save(findFood);

    }

    public void deleteFood(long foodId) {
        Food food = findVerifiedFood(foodId);
        foodRepository.delete(food);

    }

    public Food findVerifiedFood(long foodId) { //현재 존재하는지 확인하는 기능 (find, update, delete)
        Optional<Food> optionalFood = foodRepository.findById(foodId); //npe 발생 방지 optional 사용
        Food findFood = optionalFood.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findFood;
    }

    private void verifyExistFood(String foodName) { //생성 시 이미 존재하는지 확인하는 기능 (create)
        Optional<Food> food = foodRepository.findByFoodName(foodName);
        if(food.isPresent()) throw new BusinessLogicException(ExceptionCode.NOT_FOUND);
    }


}
