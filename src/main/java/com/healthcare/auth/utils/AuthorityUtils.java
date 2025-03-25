package com.healthcare.auth.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorityUtils {
    /*  application.yml 다음과 같이 추가한다.
    mail:
      address:
        admin: admin@gmail.com
        application.yml 파일에 admin 주소가 동일한지 확인하자.

     */
    @Value("${mail.address.admin}")
    private String adminMailAddress; //최고 관리자 메일 주소

    //미리 만들어진 AuthorityUtils Class 에서 createAuthorityList 를 이용해서 ADMIN, USER 에 대한 권한을 구현했습니다.
    //ADMIN_ROLES, USER_ROLES  변수에 - 사용자들을 넣어주면 되는 것이며 (List)형태입니다.
    private final List<GrantedAuthority> ADMIN_ROLES = org.springframework.security.core.authority.AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    private final List<GrantedAuthority> USER_ROLES = org.springframework.security.core.authority.AuthorityUtils.createAuthorityList("ROLE_USER");

    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");

    //이메일 별로 권한 만드는 기능  순서는 admin - user 아래로 갈수록 좁아져야 합니다.
    public List<String> createRoles(String email) {
        if(email.equals(adminMailAddress)) {
            return ADMIN_ROLES_STRING;
        }
        else {
            return USER_ROLES_STRING;
        }
    }

    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        //List를 순회하며 String을 GrantedAuthority로 바꿔서 모두 다시 List로 패키징 후 반환
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
