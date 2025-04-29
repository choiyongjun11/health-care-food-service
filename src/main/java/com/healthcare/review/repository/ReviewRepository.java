package com.healthcare.review.repository;

import com.healthcare.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review,Long> { //리뷰에는 null 값이 들어갈 수 도 있다.
    //음식에 대한 리뷰를 페이징처리하여 조회
    //음식을 기준으로 해당 음식에 대한 리뷰를 조회하는 메서드 (Pageable 사용)
    //음식 id를 기준으로 조회
    Page<Review> findByFood_FoodId(long foodId, Pageable pageable);
    List<Review> findByMember_MemberId(Long memberId);
}
