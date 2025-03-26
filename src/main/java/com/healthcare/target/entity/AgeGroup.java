package com.healthcare.target.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AgeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ageGroupId;
    private String ageGroupName;
    //mapping 관계 설정 AgeGroup (1) <-> Target (N) 1:N 관계
    //mapping 관계 설정 AgeGroup (1) <-> AgeGroupFood (N) 1:N 관계
}
