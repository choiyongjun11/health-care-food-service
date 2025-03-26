package com.healthcare.target.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "target_effect")
public class TargetEffect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long targetEffectId;

    //mapping 관계 설정 TargetEffect (N) <-> GoalType (1) N:1 관계
    private long goalTypeId; //fk
    //mapping 관계 설정 TargetEffect (N) <-> Effect (1) N:1 관계
    private long effectId; //fk
}
