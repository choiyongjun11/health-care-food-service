package com.healthcare.member.repository;

import com.healthcare.member.entity.Member;
import com.healthcare.member.entity.MemberTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository <Member, Long> {
    Optional<Member> findByEmail(String email);

}
