package com.healthcare.target.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "goal_type")
public class GoalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long goalTypeId;
    @Column(nullable = false)
    private String goalTypeCategory;
    @Column(nullable = false)
    private String goalTypeName;

    //mapping 관계 설정 GoalType (1) <-> Target (N) 1:N 관계
    @OneToMany(mappedBy = "goalType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Target> targets = new ArrayList<>();

    //mapping 관계 설정 GoalType (1) <-> TargetEffect (N) 1:N 관계
    @OneToMany(mappedBy = "goalType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TargetEffect> targetEffects = new ArrayList<>();
}
