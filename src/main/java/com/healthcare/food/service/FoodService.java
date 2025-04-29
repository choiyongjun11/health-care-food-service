package com.healthcare.food.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.entity.Food;
import com.healthcare.food.entity.FoodLike;
import com.healthcare.food.entity.FoodView;
import com.healthcare.food.mapper.FoodLikeMapper;
import com.healthcare.food.mapper.FoodMapper;
import com.healthcare.food.repository.FoodLikeRepository;
import com.healthcare.food.repository.FoodRepository;
import com.healthcare.food.repository.FoodViewRepository;
import com.healthcare.member.entity.Member;
import com.healthcare.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final FoodLikeRepository foodLikeRepository;
    private final MemberRepository memberRepository;
    private final FoodLikeMapper foodLikeMapper;
    private final FoodViewRepository foodViewRepository;

    public FoodService(FoodRepository foodRepository,
                       FoodLikeRepository foodLikeRepository,
                       MemberRepository memberRepository,
                       FoodLikeMapper foodLikeMapper,
                       FoodViewRepository foodViewRepository) {
        this.foodRepository = foodRepository;
        this.foodLikeRepository = foodLikeRepository;
        this.memberRepository = memberRepository;
        this.foodLikeMapper = foodLikeMapper;
        this.foodViewRepository = foodViewRepository;
    }

    public Food createFood(Food food) {
        verifyExistFood(food.getFoodName());
        return foodRepository.save(food);
    }

    public Food findFood(long foodId) {
        return findVerifiedFood(foodId);
    }

    public Page<Food> findFoods(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return foodRepository.findAll(pageable);
    }

    public Food updateFood(Food food) {
        Food findFood = findVerifiedFood(food.getFoodId());
        Optional.ofNullable(food.getFoodName()).ifPresent(findFood::setFoodName);
        Optional.ofNullable(food.getFoodImageUrl()).ifPresent(findFood::setFoodImageUrl);
        return foodRepository.save(findFood);
    }

    public void deleteFood(long foodId) {
        Food food = findVerifiedFood(foodId);
        foodRepository.delete(food);
    }

    //조회수 가져오기
    public int getFoodViewCount(Food food) {
        return foodViewRepository.sumViewCountByFood(food);
    }
    //좋아요 수 가져오기
    public int getFoodLikeCount(Food food) {
        return foodLikeRepository.sumLikeCountByFood(food);
    }

    //로그인 한 사용자의 username(email)을 받아서 member 찾고 foodview 엔티티를 새로 만들어 저장
    public void increaseViewCount(String username, Food food) {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        foodViewRepository.save(new FoodView(food, member));
    }

    public boolean isFoodLikedByUsername(String username, Food food) {
        Optional<Member> member = memberRepository.findByEmail(username);
        if (member.isEmpty()) return false;
        return foodLikeRepository.existsByMember_MemberIdAndFood(member.get().getMemberId(), food);
    }

    public FoodDto.LikeResponse toggleFoodLike(String username, Long foodId) {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        Optional<FoodLike> optionalFoodLike = foodLikeRepository.findByMemberAndFood(member, food);

        boolean liked;
        if (optionalFoodLike.isPresent()) {
            foodLikeRepository.delete(optionalFoodLike.get());
            liked = false;
        } else {
            FoodLike foodLike = new FoodLike();
            foodLike.setMember(member);
            foodLike.setFood(food);
            foodLike.setLikeCount(1L);
            foodLike.setLikeDate(LocalDate.now());
            foodLikeRepository.save(foodLike);
            liked = true;
        }
        int likeCount = foodLikeRepository.sumLikeCountByFood(food);
        return foodLikeMapper.foodToLikeResponse(food, likeCount, liked);
    }

    private Food findVerifiedFood(long foodId) {
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
    }

    private void verifyExistFood(String foodName) {
        Optional<Food> food = foodRepository.findByFoodName(foodName);
        if (food.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.NOT_FOUND);
        }
    }

}
