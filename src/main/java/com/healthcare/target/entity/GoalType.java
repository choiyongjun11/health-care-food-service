package com.healthcare.target.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "goal_type")
public class GoalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long goalTypeId;
    private String goalTypeCategory;
    private String goalTypeName;

    //mapping 관계 설정 GoalType (1) <-> Target (N) 1:N 관계
    //mapping 관계 설정 GoalType (1) <-> TargetEffect (N) 1:N 관계

}
