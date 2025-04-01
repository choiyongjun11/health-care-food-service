package com.healthcare.target.dto;

import com.healthcare.food.dto.FoodDto;
import com.healthcare.target.entity.Target;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

public class TargetDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String ageGroupName;
        private String goalTypeCategory;
        private String goalTypeName;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private Long targetId;
        private String ageGroupName;
        private String goalTypeCategory;
        private String goalTypeName;

        public void setTargetId(long targetId){
            this.targetId = targetId;
        }

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private Long targetId;
        private String ageGroupName;
        private String goalTypeCategory;
        private String goalTypeName;
        private String effectName;
        private Target.TargetStatus targetStatus;
        private List<FoodDto.Response> recommendedFoods;


    }



}
