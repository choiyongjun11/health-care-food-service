package com.healthcare.food.repository;

import com.healthcare.food.entity.Food;
import com.healthcare.food.entity.FoodLike;
import com.healthcare.food.entity.FoodView;
import com.healthcare.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FoodViewRepository extends JpaRepository<FoodView, Long> {
    //null일 경우 0으로 처리한다.
    @Query("SELECT COALESCE(SUM(fv.foodViewCount), 0) FROM FoodView fv WHERE fv.food = :food")
    int sumViewCountByFood(@Param("food") Food food);
    Optional<FoodView> findByMemberAndFood(Member member, Food food);
}
