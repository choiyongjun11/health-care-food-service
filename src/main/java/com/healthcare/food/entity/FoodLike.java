package com.healthcare.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_like")
public class FoodLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodLikeId;
    //mapping 관계 설정 FoodLike (N) <-> Member (1) N:1 관계
    private long memberId; //fk
    //mapping 관계 설정 FoodLike (N) <-> Food (1) N:1 관계
    private long foodId; //fk
    private long likeCount;
    private Date likeDate;


}
