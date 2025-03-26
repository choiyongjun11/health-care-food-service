package com.healthcare.member.member_target.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member_target")
public class MemberTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberTargetId;
    private long memberId; //FK 키
    private long targetId; // FK 키


    //mapping 관계 설정 MemberTarget (N) <-> Member (1) N:1 관계

    //mapping 관계 설정 MemberTarget (N) <-> Target (1) N:1 관계



}
