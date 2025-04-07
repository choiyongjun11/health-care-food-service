package com.healthcare.food.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.food.entity.Food;
import com.healthcare.food.repository.FoodLikeRepository;
import com.healthcare.food.repository.FoodRepository;
import com.healthcare.food.repository.FoodViewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {
    private final FoodLikeRepository foodLikeRepository;
    private final FoodViewRepository foodViewRepository;
    //create, find, update, delete
    private FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository, FoodLikeRepository foodLikeRepository, FoodViewRepository foodViewRepository) {
        this.foodRepository = foodRepository;
        this.foodLikeRepository = foodLikeRepository;
        this.foodViewRepository = foodViewRepository;
    }

    public Food createFood(Food food) {
        verifyExistFood(food.getFoodName());
         return foodRepository.save(food);

    }

    public Food findFood(long foodId) {
        return findVerifiedFood(foodId);

    }

    public Page<Food> findFoods (int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return foodRepository.findAll(pageable);
    }

   public int getFoodLikeCount(Food food) {
        return foodLikeRepository.countByFood(food);
   }

   public int getFoodViewCount(Food food) {
        return foodViewRepository.countByFood(food);
   }

    public Food updateFood(Food food) {
        Food findFood = findVerifiedFood(food.getFoodId());
        Optional.ofNullable(food.getFoodName()).ifPresent(foodName -> findFood.setFoodName(foodName));
        Optional.ofNullable(food.getFoodImageUrl()).ifPresent(imageUrl -> findFood.setFoodImageUrl(imageUrl));
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
