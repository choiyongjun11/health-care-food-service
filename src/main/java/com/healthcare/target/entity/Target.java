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
    private Long targetId;

    //mapping 관계 설정 Target (1) <-> MemberTarget (N) 1:N 관계
    @OneToMany(mappedBy = "target",cascade = CascadeType.ALL, orphanRemoval = true)
    private List <MemberTarget> memberTargets = new ArrayList<>();

    // 영속성 전이를 활용하여 Target 을 불렀을 때 goalType,  ageGroup의 정보를 가져오거나 셋팅해야한다.
    // 중복된 정보를 셋팅을 하면 안된다는 주의 점이 있습니다. (중복 방지) 필요함.
    // 하지만 JPA 에서 엔티티를 비교 시 기본은 객체의 메모리 주소 비교한는 것이다.
    // 같은 값을 가진 엔티티여도 새로 생성한 객체는 다르다 라는 문제가 생겨 각 엔티티에서 equals(),hashcode() 를 활용하여 해결해보자.

    //mapping 관계 설정 Target (N) <-> GoalType (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "goal_type_id")
    private GoalType goalType; //FK 키

    //mapping 관계 설정 Target (N) <-> AgeGroup (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "age_group_id")
    private AgeGroup ageGroup; //FK 키

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TargetStatus targetStatus = TargetStatus.TARGET_DEACTIVED; //enum 으로 구현

    public enum TargetStatus {
        TARGET_DEACTIVED ("건강 목표 비활성화"),
        TARGET_REGISTERED ("건강 목표 활성화"),
        TARGET_CHANGED("건강 목표 변경"),
        TARGET_DELETED("건강 목표 삭제");

        @Getter
        private String status;
        TargetStatus(String status) {
            this.status = status;
        }

    }

}
