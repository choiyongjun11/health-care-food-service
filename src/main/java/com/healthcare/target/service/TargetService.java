package com.healthcare.target.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;

import com.healthcare.target.dto.TargetDto;
import com.healthcare.target.entity.AgeGroup;
import com.healthcare.target.entity.GoalType;
import com.healthcare.target.entity.Target;
import com.healthcare.target.mapper.TargetMapper;
import com.healthcare.target.repository.*;
import org.springframework.stereotype.Service;


//agegroup, goaltype 연관 엔티티를 db에서 찾아와야하기에 service 에서 dto -> entity로 변환한다.
//mapper 작업을 service에서 한다.

//왜 Service에서 DTO → Entity 매핑했는가?
//@Mapping으로 goalTypeName 같은 문자열만 넣으면 JPA 입장에서 연관관계가 설정되지 않음 → 단지 값만 복사될 뿐임
//그래서 반드시 DB에서 해당 GoalType, AgeGroup을 조회해서 target.setXXX() 해줘야 정확한 연관관계가 설정됨
//이 로직은 비즈니스 로직의 일부이기 때문에 → Service에서 처리하는 게 맞음

@Service
public class TargetService {
    //저장소, entity, mapper
    //create,find, update, delete,

    private final TargetMapper targetMapper;
    private final TargetRepository targetRepository;
    private final AgeGroupRepository ageGroupRepository;
    private final GoalTypeRepository goalTypeRepository;

    public TargetService(TargetMapper targetMapper, TargetRepository targetRepository, AgeGroupRepository ageGroupRepository, GoalTypeRepository goalTypeRepository) {
        this.targetMapper = targetMapper;
        this.targetRepository = targetRepository;
        this.ageGroupRepository = ageGroupRepository;
        this.goalTypeRepository = goalTypeRepository;
    }

    public Target createTarget(TargetDto.Post requestBody) {
        Target target = targetMapper.targetPostToTarget(requestBody);
        GoalType goalType = goalTypeRepository.findByGoalTypeCategoryAndGoalTypeName
                (requestBody.getGoalTypeCategory(), requestBody.getGoalTypeName())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        AgeGroup ageGroup = ageGroupRepository.findByAgeGroupName(requestBody.getAgeGroupName())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

       target.setGoalType(goalType);
       target.setAgeGroup(ageGroup);

        //중복된 데이터이면 등록 안되게 해야 한다. (중복검사)
        verifyTarget(target);

       target.setTargetStatus(Target.TargetStatus.TARGET_REGISTERED);

        return targetRepository.save(target);
    }

    //중복 방지 (건강 목표가 중복이면 안됨)
    public void verifyTarget(Target target) {
        if(targetRepository.findByGoalTypeAndAgeGroup(
                target.getGoalType(), target.getAgeGroup()).isPresent()) {
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_TARGET);
        }
    }

    //단일 조회
    public Target findTarget(long targetId) {
        return targetRepository.findById(targetId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
    }

    public Target updateTarget(TargetDto.Patch requestBody) {
        Target originalTarget = targetRepository.findById(requestBody.getTargetId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        GoalType goalType = goalTypeRepository.findByGoalTypeCategoryAndGoalTypeName(
                requestBody.getGoalTypeCategory(), requestBody.getGoalTypeName())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        AgeGroup ageGroup = ageGroupRepository.findByAgeGroupName(requestBody.getAgeGroupName())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        originalTarget.setGoalType(goalType);
        originalTarget.setAgeGroup(ageGroup);

        return targetRepository.save(originalTarget);

    }

    public void deleteTarget(long targetId) {
    Target target = targetRepository.findById(targetId)
            .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
    targetRepository.delete(target);
    }






    //나이대별 추천 기능

    //음식 추천 리스트 변환




}
