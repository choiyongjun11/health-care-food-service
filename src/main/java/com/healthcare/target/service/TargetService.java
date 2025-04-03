package com.healthcare.target.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;

import com.healthcare.target.entity.AgeGroup;
import com.healthcare.target.entity.GoalType;
import com.healthcare.target.entity.Target;
import com.healthcare.target.mapper.TargetMapper;
import com.healthcare.target.repository.*;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class TargetService {
    //저장소, entity, mapper
    //create,find, update, delete,

    private final TargetMapper targetMapper;
    private final TargetRepository targetRepository;

    public TargetService(TargetMapper targetMapper, TargetRepository targetRepository) {
        this.targetMapper = targetMapper;
        this.targetRepository = targetRepository;

    }

    public Target createTarget(Target target) {
        //중복 검증
        Optional<Target> optionalTarget = targetRepository.findByGoalTypeAndAgeGroup(target.getGoalType(), target.getAgeGroup());
        if (optionalTarget.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.NOT_FOUND);
        }
        target.setTargetStatus(Target.TargetStatus.TARGET_REGISTERED);
        return targetRepository.save(target);

    }

    public Target findTarget(long targetId) {
        return findVerifiedTarget(targetId);
    }

    public Target updateTarget(Target target) {
        Target changeTarget = findVerifiedTarget(target.getTargetId());
        changeTarget.setAgeGroup(target.getAgeGroup());
        changeTarget.setGoalType(target.getGoalType());
        changeTarget.setTargetStatus(Target.TargetStatus.TARGET_CHANGED);
        return targetRepository.save(changeTarget);
    }

    public void deleteTarget(long targetId) {
        Target target = findVerifiedTarget(targetId);
        targetRepository.delete(target);
    }


    //현재 존재하는지 확인하는 기능
    public Target findVerifiedTarget(long targetId) {
        Optional<Target> target = targetRepository.findByGoalTypeAndAgeGroup(new GoalType(),new AgeGroup());
        Target findTarget = target.orElseThrow(()-> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findTarget;
    }

    //나이대별 추천 기능

    //음식 추천 리스트 변환




}
