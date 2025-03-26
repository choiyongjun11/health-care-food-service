package com.healthcare.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodId;
    private String foodName;
    private int viewCount;
    private LocalDateTime foodCreateDate = LocalDateTime.now(); // 등록 날짜 자동 설정

    //mapping 관계 설정 Food (1) <-> AgeGroupFood (N) 1:N 관계
    //mapping 관계 설정 Food (1) <-> FoodEffect (N) 1:N 관계
    //mapping 관계 설정 Food (N) <-> Review (1) N:1 관계
    //mapping 관계 설정 Food (1) <-> FoodLike (N) 1:N 관계
    //mapping 관계 설정 Food (N) <-> FoodPhoto (1) N:1 관계
    //mapping 관계 설정 Food (1) <-> Recipe (N) 1:N 관계


}
