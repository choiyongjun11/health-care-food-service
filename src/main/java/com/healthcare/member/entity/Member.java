package com.healthcare.member.entity;

import com.healthcare.food.entity.FoodLike;
import com.healthcare.food.entity.FoodView;
import com.healthcare.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true) //unique 제약조건으로 중복 회원가입 방지
    private String email;

    @Column(nullable = false, length = 100) //access_token, refresh_token 발행으로 길이 100
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(length = 13, nullable = false, unique = true) //unique 제약조건으로 중복 회원가입을 방지
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER) //spring security - 권한 설정
    private List<String> roles = new ArrayList<>();

    //회원 정보 수정 메서드
    public void updateProfile (String name, LocalDate birthday, String phone) {
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
    }

    /*
     cascade 영속성 정의, 부모가 자식에게 영속성 전달. 부모 엔터티가 삭제되면 자식 엔터티도 삭제된다.
    영속성 전이 모두 적용 - CascadeType.ALL
    영속성 전이 저장 - CascadeType.PERSIST
    영속성 전이 삭제 - CascadeType.REMOVE
    orphanRemoval - 고아객체 제거, 부모 엔터티와 연관관계가 끊어진 자식 엔터티를 자동으로 삭제하는 기능.
    Casecade.Type.All, orphanRemoval = true - 부모 엔터티를 통해서 자식의 생명 주기를 관리한다.
     */

    //mapping 관계 설정 Member (1) <-> MemberTarget (N) 1:N 관계
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, orphanRemoval = true)
    private List <MemberTarget> memberTargets = new ArrayList<>();

    //mapping 관계 설정 Member (1) <-> Review (N) 1:N 관계
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    //mapping 관계 설정 Member (1) <-> FoodLike (N) 1:N 관계
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <FoodLike> foodLikes = new ArrayList<>();

    //mapping 관계 설정 Member (1) <-> FoodView (N) 1:N 관계
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <FoodView> foodViews = new ArrayList<>();

}
