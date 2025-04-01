package com.healthcare.review.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.food.entity.Food;
import com.healthcare.food.repository.FoodRepository;
import com.healthcare.member.entity.Member;
import com.healthcare.member.repository.MemberRepository;
import com.healthcare.review.dto.ReviewDto;
import com.healthcare.review.entity.Review;
import com.healthcare.review.mapper.ReviewMapper;
import com.healthcare.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    //create,find, update, delete
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final ReviewMapper mapper;

    public ReviewService(ReviewRepository reviewRepository,
                         MemberRepository memberRepository,
                         FoodRepository foodRepository, ReviewMapper mapper) {
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
        this.foodRepository = foodRepository;
        this.mapper = mapper;
    }

    public Review createReview (Review review, long foodId, long memberId) {
        //1. 회원가입 한 사용자가 존재하는지 member 에 대한 검증이 필요합니다.
        //2. 특정 음식에 리뷰를 추가하는 것이기에 해당 음식이 존재하는지 확인이 필요합니다.
        Member member = verifyExistMember(memberId);
        Food food = verifyExistFood(foodId);

        review.setMember(member);
        review.setFood(food);
        return reviewRepository.save(review);
    }
    //리뷰 목록 조회 (페이지네이션)
    public Page<Review> getReviews(long foodId, int page, int size) {
        verifyExistFood(foodId);
        Pageable pageable = PageRequest.of(page -1, size); //page 번호 0 부터 시작
        return reviewRepository.findByFood(foodId, pageable);
    }

    //수정할 content에 null 인 경우도 고려하여 설정한다.
    // 수정 시 리뷰 란에 빈칸 or 내용이 없으면 수정이 불가되어야한다.

    public Review updateReview(Review review,long foodId) {
        Review findReview = reviewRepository.findById(review.getReviewId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        //food id 일치 여부 검증
        if (findReview.getFood().getFoodId() != foodId) {
            throw new BusinessLogicException(ExceptionCode.NOT_FOUND);
        }

        if (review.getContent() != null) {
            findReview.setContent(review.getContent());
        }

        return reviewRepository.save(findReview);
    }

    public void deleteReview(long reviewId, long foodId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        if(review.getFood().getFoodId() != foodId) {
            throw new BusinessLogicException(ExceptionCode.NOT_FOUND);
        }
        reviewRepository.delete(review);
    }

    //회원 존재여부
    private Member verifyExistMember(long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Member findMember = member.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findMember;
    }

    //음식 존재여부
    private Food verifyExistFood(long foodId) {
        Optional<Food> food = foodRepository.findById(foodId);
        Food findFood = food.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findFood;
    }




}
