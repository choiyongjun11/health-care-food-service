package com.healthcare.food.entity;

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
@Table(name = "food_photo")
public class FoodPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodPhotoId; //래퍼형

    //mapping 관계 설정 FoodPhoto (1) <-> Food (N) 1:N 관계
    @OneToMany(mappedBy = "foodPhoto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> foods = new ArrayList<>(); //FK

    @Column(nullable = false)
    private String foodImageUrl; //type 바뀔 수 있음. 임의로 설정
}
