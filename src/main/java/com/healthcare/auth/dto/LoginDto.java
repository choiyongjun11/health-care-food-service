package com.healthcare.auth.dto;

import lombok.Getter;

@Getter
public class LoginDto {
    private String email;
    private String password;
    private String memberId;
}
