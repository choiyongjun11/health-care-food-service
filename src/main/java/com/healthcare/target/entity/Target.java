package com.healthcare.target.entity;


import com.healthcare.member.entity.MemberTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "target")
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long targetId;

    //mapping 관계 설정 Target (1) <-> MemberTarget (N) 1:N 관계
    @OneToMany(mappedBy = "target",cascade = CascadeType.ALL, orphanRemoval = true)
    private List <MemberTarget> memberTargets = new ArrayList<>();

    //mapping 관계 설정 Target (N) <-> GoalType (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "goalType_id")
    private GoalType goalType; //FK 키

    //mapping 관계 설정 Target (N) <-> AgeGroup (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "ageGroup_id")
    private AgeGroup ageGroup; //FK 키

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
