package com.healthcare.target.repository;

import com.healthcare.target.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TargetRepository extends JpaRepository<Target, Long> {

}
