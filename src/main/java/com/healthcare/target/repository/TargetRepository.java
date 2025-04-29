package com.healthcare.target.repository;

import com.healthcare.review.entity.Review;
import com.healthcare.target.entity.AgeGroup;
import com.healthcare.target.entity.GoalType;
import com.healthcare.target.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TargetRepository extends JpaRepository<Target, Long> {
    Optional<Target> findByGoalTypeAndAgeGroup(GoalType goalType, AgeGroup ageGroup);

}
