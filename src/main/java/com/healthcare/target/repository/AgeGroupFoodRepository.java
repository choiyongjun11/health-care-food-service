package com.healthcare.target.repository;

import com.healthcare.target.entity.AgeGroup;
import com.healthcare.target.entity.AgeGroupFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgeGroupFoodRepository extends JpaRepository<AgeGroupFood, Long> {
    List<AgeGroupFood> findByAgeGroup(AgeGroup ageGroup); //특정나이대에 맞는 음식 리스트 가져오기
}
