package com.healthcare.target.repository;

import com.healthcare.target.entity.AgeGroup;
import com.healthcare.target.entity.AgeGroupFood;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AgeGroupRepository extends JpaRepository<AgeGroup, Long> {
    Optional<AgeGroup> findByAgeGroupName(String ageGroupName);  // 나이대별 AgeGroup 찾기
    List<AgeGroupFood> findByAgeGroup(AgeGroup ageGroup);
}
