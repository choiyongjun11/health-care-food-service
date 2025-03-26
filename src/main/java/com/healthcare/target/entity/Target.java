package com.healthcare.target.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "target")
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long targetId;

    //mapping 관계 설정 Target (1) <-> MemberTarget (N) 1:N 관계

    //mapping 관계 설정 Target (N) <-> GoalType (1) N:1 관계
    private long goalTypeId; //fk

    //mapping 관계 설정 Target (N) <-> AgeGroup (1) N:1 관계
    private long ageGroupId; //fk

    @Column(nullable = false)
    private TargetStatus targetStatus = TargetStatus.TARGET_DEACTIVED; //enum 으로 구현

    public enum TargetStatus {
        TARGET_DEACTIVED ("건강 목표 비활성화"),
        TARGET_REGISTERED ("건강 목표 활성화");

        @Getter
        private String status;
        TargetStatus(String status) {
            this.status = status;
        }

    }


}
