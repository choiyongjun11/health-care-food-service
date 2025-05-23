package com.healthcare.food.entity;

import com.healthcare.recipe.entity.Recipe;
import com.healthcare.review.entity.Review;
import com.healthcare.target.entity.AgeGroupFood;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodId;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private String foodCategory;

    @Column(nullable = false)
    private String foodImageUrl; //type 바뀔 수 있음. 임의로 설정

    @Column(nullable = false)
    private String foodDescription; //설명글

    @Column(nullable = false)
    private LocalDate foodCreateDate = LocalDate.now(); // 등록 날짜 자동 설정

    //mapping 관계 설정 Food (1) <-> Review (N) 1:N 관계
    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    //mapping 관계 설정 Food (1) <-> AgeGroupFood (N) 1:N 관계
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgeGroupFood> ageGroupFoods = new ArrayList<>();

    //mapping 관계 설정 Food (1) <-> FoodRecommend (N) 1:N 관계
    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodRecommend> foodRecommends = new ArrayList<>();

    //mapping 관계 설정 Food (1) <-> FoodLike (N) 1:N 관계
    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodLike> foodLikes = new ArrayList<>();

    //mapping 관계 설정 Food (1) <-> FoodView (N) 1:N 관계
    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodView> foodViews = new ArrayList<>();

    //mapping 관계 설정 Food (1) <-> Recipe (1) 1:1 관계
    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private Recipe recipe;

    //mapping 관계 설정 Food(1) <-> FoodIngredient
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodIngredient> foodIngredient = new ArrayList<>();

}
