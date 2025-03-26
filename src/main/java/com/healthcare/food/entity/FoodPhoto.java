package com.healthcare.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_photo")
public class FoodPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodPhotoId;

    //mapping 관계 설정 FoodPhoto (1) <-> Food (N) 1:N 관계
    private long foodId; //fk

    private String image; //type 바뀔 수 있음. 임의로 설정
}
