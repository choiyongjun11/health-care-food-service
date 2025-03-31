package com.healthcare.target.entity;

import com.healthcare.food.entity.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "age_group_food")
public class AgeGroupFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ageGroupFoodId;

    //mapping 관계 설정 AgeGroupFood (N) <-> AgeGroup (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "ageGroup_id")
    private AgeGroup ageGroup; //FK

    //mapping 관계 설정 AgeGroupFood (N) <-> Food (1) N:1 관계
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food; //FK




}
