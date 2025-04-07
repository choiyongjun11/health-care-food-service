package com.healthcare.food.repository;

import com.healthcare.food.entity.Food;
import com.healthcare.food.entity.FoodLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FoodLikeRepository extends JpaRepository<FoodLike, Long> {
    int countByFood(Food food);
}
