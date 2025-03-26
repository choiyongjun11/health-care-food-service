package com.healthcare.target.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "agegroup_food")
public class AgeGroupFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ageGroupFoodId;

    //mapping 관계 설정 AgeGroupFood (N) <-> Target (1) N:1 관계
    private long ageGroupId; //fk
    //mapping 관계 설정 AgeGroupFood (N) <-> Food (1) N:1 관계
    private long foodId; //fk


}
