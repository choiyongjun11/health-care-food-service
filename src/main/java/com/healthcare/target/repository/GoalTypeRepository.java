package com.healthcare.target.repository;

import com.healthcare.target.entity.GoalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalTypeRepository extends JpaRepository<GoalType, Long> {
    Optional<GoalType> findByGoalTypeCategoryAndGoalTypeName(String goalTypeCategory, String goalTypeName);
}
