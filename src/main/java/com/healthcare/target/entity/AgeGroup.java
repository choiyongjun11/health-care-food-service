package com.healthcare.target.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "age_group")
public class AgeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ageGroupId;

    @Column(nullable = false)
    private String ageGroupName;

    //mapping 관계 설정 AgeGroup (1) <-> Target (N) 1:N 관계
    @OneToMany(mappedBy = "ageGroup",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Target> targets = new ArrayList<>();

    //mapping 관계 설정 AgeGroup (1) <-> AgeGroupFood (N) 1:N 관계
    @OneToMany(mappedBy = "ageGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgeGroupFood> ageGroupFoods = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgeGroup)) return false;
        AgeGroup that = (AgeGroup) o;
        return Objects.equals(ageGroupName, that.ageGroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ageGroupName);
    }

}
