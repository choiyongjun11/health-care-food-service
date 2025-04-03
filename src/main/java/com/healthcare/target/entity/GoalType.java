package com.healthcare.target.entity;

import com.healthcare.food.entity.FoodRecommend;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    //mapping 관계 설정 GoalType (1) <-> FoodRecommend (N) 1:N 관계
    @OneToMany(mappedBy = "goalType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodRecommend> foodRecommends = new ArrayList<>();

    // equals & hashCode 비교 방식.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalType)) return false;
        GoalType that = (GoalType) o;
        return Objects.equals(goalTypeCategory, that.goalTypeCategory)
                && Objects.equals(goalTypeName, that.goalTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalTypeCategory, goalTypeName);
    }

}
