package com.healthcare.member.dto;

import com.healthcare.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotSpace
        @Email
        private String email;

        @NotSpace
        private String password;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;

        @NotSpace
        private String name;
        @NotSpace
        private Date birthday;
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 11자리로 구성되어 있으며 '-' 형태로 되어야 합니다.")
        private String phone;

//        public void setMemberId(long memberId) {
//            this.memberId = memberId;
//
//        }

    }

    @Getter
    @AllArgsConstructor
    public static class Response {

        private long memberId;
        private String email;
        private String name;
        private Date birthday;
        private String phone;


    }

}
