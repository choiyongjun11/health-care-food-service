package com.healthcare.food.entity;

import com.healthcare.target.entity.GoalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_recommend")
public class FoodRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodRecommendId;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "goal_type_id", nullable = false)
    private GoalType goalType;

    @Column(nullable = false)
    private String effect; //영향: 면역력 강회, 근육 증가

    @Column(nullable = false)
    private String reason; //추천 이유: 단백질 함량이 높아 근육 발달에 도움

}
