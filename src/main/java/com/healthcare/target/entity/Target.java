package com.healthcare.target.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "target")
public class Target {
    private int targetId;
    private String ageGroupName;
    private String goalTypeCategory;
    private String goalTypeName;
    private TargetStatus targetStatus = TargetStatus.TARGET_DEACTIVED; //enum 으로 구현


    public enum TargetStatus {
        TARGET_DEACTIVED ("건강 목표 비활성화"),
        TARGET_REGISTERED ("건강 목표 활성화");

        @Getter
        private String status;
        TargetStatus(String status) {
            this.status = status;
        }

    }


}
