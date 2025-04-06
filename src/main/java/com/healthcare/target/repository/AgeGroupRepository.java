package com.healthcare.target.repository;

import com.healthcare.target.entity.AgeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgeGroupRepository extends JpaRepository<AgeGroup, Long> {
 Optional<AgeGroup> findByAgeGroupName (String ageGroupName);
}
