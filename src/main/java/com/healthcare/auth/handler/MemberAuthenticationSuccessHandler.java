package com.healthcare.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.auth.jwt.JwtTokenizer;
import com.healthcare.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
      private final ObjectMapper objectMapper = new ObjectMapper();

      @Override
      public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                          Authentication authentication) throws IOException, ServletException {

          // Authentication 객체에서 Member 객체 추출 (Principal로 저장되어 있을 것)
          Member member = (Member) authentication.getPrincipal();

          // JWT 토큰은 이미 JwtAuthenticationFilter에서 생성한 상태일 수도 있음

          Map<String, Object> responseBody = new HashMap<>();

          responseBody.put("email", member.getEmail());
          responseBody.put("memberId", member.getMemberId());

          response.setStatus(HttpServletResponse.SC_OK);
          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8"); //한글
          objectMapper.writeValue(response.getWriter(), responseBody);

          log.info("# Authenticated successfully!");
      }
}
