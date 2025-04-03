package com.healthcare.target.repository;

import com.healthcare.target.entity.AgeGroup;
import com.healthcare.target.entity.GoalType;
import com.healthcare.target.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface TargetRepository extends JpaRepository<Target, Long> {
    Optional<Target> findByTargetId(Long targetId);
}
