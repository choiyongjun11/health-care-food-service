package com.healthcare.food.repository;

import com.healthcare.food.entity.Food;
import com.healthcare.food.entity.FoodView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodViewRepository extends JpaRepository<FoodView, Long> {
    int countByFood(Food food);
}
