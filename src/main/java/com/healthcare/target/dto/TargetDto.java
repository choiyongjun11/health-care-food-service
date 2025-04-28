package com.healthcare.target.dto;

import com.healthcare.target.entity.Target;
import lombok.*;

public class TargetDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String goalTypeCategory; //체중관련
        private String goalTypeName; //체중감소
        private String ageGroupName; //청소년

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private Long targetId;
        private String ageGroupName; //성인
        private String goalTypeCategory; //체중관련
        private String goalTypeName; //체중증가

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long targetId;
        private String ageGroupName; //성인
        private String goalTypeCategory; //체중관련
        private String goalTypeName; //체중증가
        private Target.TargetStatus targetStatus; //목표 활성화

    }

}
