package com.healthcare.target.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AgeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ageGroupId;
    @Column(nullable = false)
    private String ageGroupName;
    //mapping 관계 설정 AgeGroup (1) <-> Target (N) 1:N 관계
    //mapping 관계 설정 AgeGroup (1) <-> AgeGroupFood (N) 1:N 관계
}
