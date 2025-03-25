package com.healthcare.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.data.domain.Auditable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private long foodId;
    private String foodName;
    //private Image foodImage;
    private int viewCount;
    private int likeCount;
    private LocalDateTime foodCreateDate = LocalDateTime.now(); // 등록 날짜 자동 설정


}
