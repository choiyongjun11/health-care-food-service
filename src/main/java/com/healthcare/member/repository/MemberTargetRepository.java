package com.healthcare.member.repository;

import com.healthcare.food.entity.FoodLike;
import com.healthcare.member.entity.MemberTarget;
import com.healthcare.target.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberTargetRepository extends JpaRepository<MemberTarget, Long> {
    List<MemberTarget> findByMember_MemberId(Long memberId);
}
