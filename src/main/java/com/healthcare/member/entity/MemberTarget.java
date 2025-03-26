package com.healthcare.member.entity;

import com.healthcare.target.entity.Target;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "member_target")
public class MemberTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberTargetId;

    //mapping 관계 설정 MemberTarget (N) <-> Member (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //FK 키

    //mapping 관계 설정 MemberTarget (N) <-> Target (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "target_id")
    private Target target; //FK 키

}
