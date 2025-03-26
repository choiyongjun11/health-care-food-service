package com.healthcare.member.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(nullable = false, updatable = false, unique = true) //unqiue 제약조건으로 중복 회원가입 방지
    private String email;

    @Column(nullable = false, length = 100) //access_token, refresh_token 발행으로 길이 100
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime birthday;

    @Column(length = 13, nullable = false, unique = true) //unique 제약조건으로 중복 회원가입을 방지
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER) //spring security - 권한 설정
    private List<String> roles = new ArrayList<>();

    //회원 정보 수정 메서드
    public void updateProfile (String name, LocalDateTime birthday, String phone) {
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
    }

    //비밀번호 재설정 메서드
    public void updatePassword(String newPassword ) {
        this.password = newPassword;
    }


    //mapping 관계 설정 Member (1) <-> MemberTarget (N) 1:N 관계

    //mapping 관계 설정 Member (N) <-> Review (1) N:1 관계

    //mapping 관계 설정 Member (1) <-> FoodLike (N) 1:N 관계

    //mapping 관계 설정 Member (1) <-> FoodView (N) 1:N 관계


}
