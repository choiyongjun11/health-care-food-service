package com.healthcare.food.repository;

import com.healthcare.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByFoodName (String foodName);
}
