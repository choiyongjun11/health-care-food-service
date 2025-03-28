package com.healthcare.review.repository;

import com.healthcare.food.entity.Food;
import com.healthcare.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> { //리뷰에는 null 값이 들어갈 수 도 있다.
    //특정 음식에 대한 리뷰를 가져와야 한다.
    List<Review> findByFood(Food food); //음식 id를 기준으로 조회


}
