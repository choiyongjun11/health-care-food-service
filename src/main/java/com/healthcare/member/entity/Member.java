package com.healthcare.member.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date birthday;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    //mapping 관계 설정 Member (1) <-> MemberTarget (N) 1:N 관계

    //mapping 관계 설정 Member (N) <-> Review (1) N:1 관계

    //mapping 관계 설정 Member (1) <-> FoodLike (N) 1:N 관계

    //mapping 관계 설정 Member (1) <-> FoodView (N) 1:N 관계


}
