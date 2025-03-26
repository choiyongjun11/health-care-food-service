package com.healthcare.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    //mapping 관계 설정 MemberTarget (N) <-> Member (1) N:1 관계
    private long memberId; //FK 키

    //mapping 관계 설정 MemberTarget (N) <-> Target (1) N:1 관계
    private long targetId; // FK 키








}
