package com.healthcare.target.repository;

import com.healthcare.target.entity.GoalType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalTypeRepository extends JpaRepository<GoalType, Long> {
}
