package com.healthcare.target.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import com.healthcare.target.dto.TargetDto;
import com.healthcare.target.entity.AgeGroup;
import com.healthcare.target.entity.AgeGroupFood;
import com.healthcare.target.entity.Target;
import com.healthcare.target.mapper.TargetMapper;
import com.healthcare.target.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TargetService {
    //저장소, entity, mapper
    //create,find, update, delete,

    private final TargetMapper targetMapper;
    private final TargetRepository targetRepository;
    private final GoalTypeRepository goalTypeRepository;
    private final AgeGroupFoodRepository ageGroupFoodRepository;
    private final AgeGroupRepository ageGroupRepository;

    public TargetService(TargetMapper targetMapper, TargetRepository targetRepository,
                          GoalTypeRepository goalTypeRepository, AgeGroupFoodRepository ageGroupFoodRepository,
                         AgeGroupRepository ageGroupRepository) {

        this.targetMapper = targetMapper;
        this.targetRepository = targetRepository;
        this.goalTypeRepository = goalTypeRepository;
        this.ageGroupFoodRepository = ageGroupFoodRepository;
        this.ageGroupRepository = ageGroupRepository;
    }


    public Target createTarget(Target target) {
        return targetRepository.save(target);

    }

    public Target findTarget(long targetId) {
        return findVerifiedTarget(targetId);

    }

    public Target updateTarget(Target target) {
        Target changeTarget = findVerifiedTarget(target.getTargetId());
        return targetRepository.save(changeTarget);

    }

    public void deleteTarget(long targetId) {
        Target target = findVerifiedTarget(targetId);
        targetRepository.delete(target);
    }


    //현재 존재하는지 확인하는 기능
    public Target findVerifiedTarget(long targetId) {
        Optional<Target> target = targetRepository.findById(targetId);
        Target findTarget = target.orElseThrow(()-> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findTarget;
    }

    //나이대별 추천 기능
    public TargetDto.Response registerTarget(TargetDto.Post post) {
        // 나이대에 맞는 AgeGroup 객체를 찾기
        AgeGroup ageGroup = ageGroupRepository.findByAgeGroupName(post.getAgeGroupName())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        // 해당 나이대에 맞는 AgeGroupFood 리스트 가져오기
        List<AgeGroupFood> ageGroupFoods = ageGroupFoodRepository.findByAgeGroup(ageGroup);

//        // 음식 추천 리스트 변환
//        List<FoodDto.Response> recommendedFoods = mapFoods(ageGroupFoods);

        // 목표 저장 및 반환
        Target target = targetMapper.targetPostToTarget(post);
        Target savedTarget = targetRepository.save(target);

        TargetDto.Response response = targetMapper.targetToResponse(savedTarget);
        //response.setRecommendedFoods(recommendedFoods);

        return response;
    }

//    private List<FoodDto.Response> mapFoods(List<AgeGroupFood> ageGroupFoods) {
//        return ageGroupFoods.stream().map(ageGroupFood -> {
//            Food food = ageGroupFood.getFood();
//            return new FoodDto.Response(
//                    food.getFoodId(),
//                    food.getFoodName(),
//                    food.getFoodImageUrl(),
//                    food.getFoodRecommends().stream()
//                            .map(effect -> effect.getEffectDescription())
//                            .collect(Collectors.toList()),
//                    food.getViewCount(),
//                    food.getFoodCreateDate()
//            );
//        }).collect(Collectors.toList());
//    }



}
